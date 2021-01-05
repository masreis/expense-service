package net.expense.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.expense.model.Tag;
import net.expense.model.dto.ExpenseDTO;
import net.expense.model.dto.TagDTO;
import net.expense.repository.TagRepository;
import net.expense.service.TagService;

@Service
public class TagServiceImpl implements TagService {

  private TagRepository tagRepository;
  private ModelMapper modelMapper;

  @Autowired
  public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
    this.tagRepository = tagRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<Tag> findByName(String name) {
    return Optional.ofNullable(this.tagRepository.findByName(name));
  }

  @Override
  public Tag save(Tag tag) {
    return this.tagRepository.save(tag);
  }

  @Override
  public List<TagDTO> findByExpense(Long idExpense) {
    return this.tagRepository
        .findByExpenses_Id(idExpense)
        .stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  public List<TagDTO> saveTags(ExpenseDTO dto) {
    List<TagDTO> savedTags =
        Arrays.stream(dto.getTags().split(","))
            .map(
                tagName -> {
                  String tagStrip = tagName.strip().toLowerCase();
                  return this.findByName(tagStrip)
                      .map(found -> convert(found))
                      .orElseGet(
                          () -> {
                            Tag tag = new Tag();
                            tag.setName(tagStrip);
                            return convert(save(tag));
                          });
                })
            .collect(Collectors.toList());
    return savedTags;
  }

  public TagDTO convert(Tag tag) {
    return modelMapper.map(tag, TagDTO.class);
  }
}

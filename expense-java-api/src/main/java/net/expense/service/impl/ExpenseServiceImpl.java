package net.expense.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.expense.model.Expense;
import net.expense.model.Tag;
import net.expense.model.dto.ExpenseDTO;
import net.expense.model.dto.TagDTO;
import net.expense.repository.ExpenseRepository;
import net.expense.service.ExpenseService;
import net.expense.service.TagService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

  public ExpenseRepository expenseRepository;

  @Value("${page.size}")
  private int itemsPerPage;

  private ModelMapper modelMapper;
  private TagService tagService;

  @Autowired
  public ExpenseServiceImpl(
      ExpenseRepository expenseRepository, TagService tagService, ModelMapper modelMapper) {
    this.expenseRepository = expenseRepository;
    this.tagService = tagService;
    this.modelMapper = modelMapper;
  }

  @Override
  // TODO Maybe use an older version
  public ExpenseDTO save(ExpenseDTO expenseDto) {
    List<TagDTO> savedTags = tagService.saveTags(expenseDto);
    Expense expense = convert(expenseDto);
    //    expense.setTags(savedTags);

    Expense saved = this.expenseRepository.save(expense);
    return convert(saved);
  }

  @Override
  public List<ExpenseDTO> findAll() {
    return this.expenseRepository
        .findAll()
        .stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  @Override
  public boolean existsById(Long id) {
    return this.expenseRepository.existsById(id);
  }

  private ExpenseDTO convert(Expense expense) {
    ExpenseDTO dto = this.modelMapper.map(expense, ExpenseDTO.class);
    dto.setTags(expense.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
    return dto;
  }

  private Expense convert(ExpenseDTO dto) {
    return this.modelMapper.map(dto, Expense.class);
  }

  @Override
  public ExpenseDTO findById(Long id) {
    Optional<Expense> expense = this.expenseRepository.findById(id);
    if (expense.isPresent()) {
      return convert(expense.get());
    } else {
      throw new IllegalArgumentException("Not found");
    }
  }

  @Override
  public ExpenseDTO update(@Valid ExpenseDTO dto) {
    return null;
  }
}

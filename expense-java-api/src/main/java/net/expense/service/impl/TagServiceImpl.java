package net.expense.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.expense.model.Tag;
import net.expense.repository.TagRepository;
import net.expense.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
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
    public List<Tag> findByExpense(Long idExpense) {
        return this.tagRepository.findByExpenses_Id(idExpense);
    }

}

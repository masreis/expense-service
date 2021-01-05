package net.expense.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import net.expense.model.Tag;
import net.expense.model.dto.ExpenseDTO;
import net.expense.model.dto.TagDTO;

public interface TagService {

  Optional<Tag> findByName(String name);

  Tag save(Tag tag);

  List<TagDTO> findByExpense(Long idExpense);

  List<TagDTO> saveTags(@Valid ExpenseDTO dto);
}

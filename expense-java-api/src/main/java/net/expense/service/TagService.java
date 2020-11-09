package net.expense.service;

import java.util.List;
import java.util.Optional;

import net.expense.model.Tag;

public interface TagService {

    Optional<Tag> findByName(String name);

    Tag save(Tag tag);

    List<Tag> findByExpense(Long idExpense);

}
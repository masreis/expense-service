package net.expense.service;

import java.util.List;
import java.util.Optional;

import net.expense.model.Expense;

public interface ExpenseService {

    Expense save(Expense expense);

    List<Expense> findAll();

    boolean existsById(Long id);

    Optional<Expense> findById(Long id);

}
package net.expense.service;

import java.util.List;

import javax.validation.Valid;

import net.expense.model.dto.ExpenseDTO;

public interface ExpenseService {

  ExpenseDTO save(ExpenseDTO expenseDto);

  List<ExpenseDTO> findAll();

  boolean existsById(Long id);

  ExpenseDTO findById(Long id);

  ExpenseDTO update(@Valid ExpenseDTO dto);
}

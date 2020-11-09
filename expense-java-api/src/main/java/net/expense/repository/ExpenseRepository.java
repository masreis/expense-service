package net.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.expense.model.Expense;

public interface ExpenseRepository  extends JpaRepository<Expense, Long>{

}

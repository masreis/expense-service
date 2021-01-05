package net.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.expense.model.Expense;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Long>{

}

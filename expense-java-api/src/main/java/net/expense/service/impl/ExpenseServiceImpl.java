package net.expense.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.expense.model.Expense;
import net.expense.repository.ExpenseRepository;
import net.expense.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    public ExpenseRepository expenseRepository;

    @Value("${page.size}")
    private int itemsPerPage;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense save(Expense expense) {
        return this.expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return this.expenseRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return this.expenseRepository.existsById(id);
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return this.expenseRepository.findById(id);
    }

}

package net.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.expense.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

    List<Tag> findByExpenses_Id(Long idExpense);

}

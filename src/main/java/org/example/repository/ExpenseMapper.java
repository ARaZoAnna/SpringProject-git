package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Expense;

import java.util.List;

@Mapper
public interface ExpenseMapper {

    void saveExpense(Expense expense);

    List<Expense> selectAllExpesne();

    List<Expense> selectExpenseByDate(String inputdate);

    void updateExpense(Expense expense);

    List<Expense> selectByDateAndSmallCategory(Expense expense);

    void deleteExpense(Expense expense);
}

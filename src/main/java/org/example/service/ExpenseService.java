package org.example.service;

import org.example.domain.Expense;
import org.example.repository.ExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseMapper expenseMapper;

    public void saveExpense(Expense expense){
        expenseMapper.saveExpense(expense);
    }

    public List<Expense> selectAllExpense(){
        return expenseMapper.selectAllExpesne();
    }

    public List<Expense> selectExpenseByDate(String inputdate){
        return expenseMapper.selectExpenseByDate(inputdate);
    }

    //비용(수량과 금액) 수정
    public Boolean updateExpense(Expense expense){
        //날짜, 소분류, 제품이름을 통해 데이터 유무를 확인
        if(selectByDateAndSmallCategory(expense).size() > 0){
            //수량과 금액을 수정
            expenseMapper.updateExpense(expense);
            return true;
        }
        return false;
    }

    public List<Expense> selectByDateAndSmallCategory (Expense expense){
        return expenseMapper.selectByDateAndSmallCategory(expense);
    }

    public Boolean deleteExpense(Expense expense){
        if(selectByDateAndSmallCategory(expense).size() > 0){
            expenseMapper.deleteExpense(expense);
            return true;
        }
        return false;
    }
}

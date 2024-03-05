package org.example.service;

import org.example.domain.Category;
import org.example.repository.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void saveCategory(Category category){
        categoryMapper.saveCategory(category);
    }

    public List<Category> selectAllCategory(){
        return categoryMapper.selectAllCategory();
    }

    public List<Category> selectByCategory(String mediumdata){
        return categoryMapper.selectByCategory(mediumdata);
    }

    //카테고리 제품정보 수정
    public Boolean updateCategory(Category origin, Category modify){
        //데이터가 있는지 확인
        if (selectforConfirm(origin).size() > 0 ){
            //데이터가 있다면 기존 데이터의 거래완료일 변경
            categoryMapper.updateCategory(origin, modify);
            //새로운 제품 정보 생성
            saveCategory(modify);
            //업데이트 성공을 알려줌
            return true;
        }
        return false; //기존 데이터가 없어서 업데이트 실패

    }

    //기존에 수정할 기존 제품정보가 있는지 확인하는 함수
    public List<Category> selectforConfirm(Category category){
        return  categoryMapper.selectforConfirm(category);
    }

    // category 입력을해서 데이터가 있으면 삭제
    public Boolean deleteCategory(Category category){
        if (selectforConfirm(category).size() > 0){
            categoryMapper.deleteCategory(category);
            return true;
        }
        return false;
    }

}

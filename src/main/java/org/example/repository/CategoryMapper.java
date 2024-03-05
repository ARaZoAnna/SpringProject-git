package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Category;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    void saveCategory(Category category);

    List<Category> selectAllCategory();

    List<Category> selectByCategory(String mediumdata);

    //리스트 수정
    void updateCategory(Category origin, Category modify);

    //데이터가 있는지 확인하는 함수
    List<Category> selectforConfirm(Category category);

    //Category 데이터 입력으로 삭제함
    void deleteCategory(Category category);

    Category findSmallCategory(Map<String, String> nameDate);
}

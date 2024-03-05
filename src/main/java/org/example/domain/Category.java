package org.example.domain;

import lombok.*;

import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    //제품 대분류
    private String largeCategory;
    //제품 중분류
    private String mediumCategory;
    //제품 소분류
    private String smallCategory;
    //거래 시작일
    private String startDate;
    //거래 종료일
    private String endDate;
    //상품명
    private String productName;
    //상품구분(정량)
    private Integer productQuantity;
    //매매가
    private Integer productAmount;


    //MAP 타입을 Category형식으로 바꾸기 위한 생성자 (자바에는 JSON 타입이 없어서)
    public Category(Map<String, Object> m){
        this.setLargeCategory((String)m.get("largeCategory"));
        this.setMediumCategory((String)m.get("mediumCategory"));
        this.setSmallCategory((String)m.get("smallCategory"));
        this.setStartDate((String)m.get("startDate"));
        this.setEndDate((String)m.get("endDate"));
        this.setProductName((String)m.get("productName"));
        this.setProductQuantity((Integer)m.get("productQuantity"));
        this.setProductAmount((Integer) m.get("productAmount"));
    }
}

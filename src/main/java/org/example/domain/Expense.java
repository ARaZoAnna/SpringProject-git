package org.example.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Expense {

    //구매 비용 날짜
    private String date;
    //비용 소분류
    private String smallCategory;
    //비용 구매상품 이름
    private String productName;
    //비용 구매상품 수량
    private Integer expenseCount;
    //비용 구매상품 금액
    private Integer expenseAmount;
}

package org.example.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Purchase {
    //매입 날짜
    private String date;
    //매입 소분류(기존에 있는 데이터)
    private String smallCategory;
    //매입 상품명(기존에 있는 데이터
    private String productName;
    //매입 수량
    private Integer purchaseAmount;
    //매입 금액
    private Integer purchaseCount;


}

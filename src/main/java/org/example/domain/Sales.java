package org.example.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Sales {
    //고유번호 (주문번호)
    String ID;
    //구매 날짜
    String date;
    //소분량 (제품명에서 추출)
    String smallCategory;
    //판매채널 (초기화 : 0, 농라 : 1, 스마트스토어 :2...)
    Integer channel;
    //판매 제품명(실제 판매시 제품이름)
    String productName;
    //지불방법(초기화 : 0, 카드 : 1, 현금 : 2)
    Integer payway;
    //판매 수량
    Integer salesCount;
    //판매 금액
    Integer salesAmount;

}

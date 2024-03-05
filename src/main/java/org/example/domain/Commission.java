package org.example.domain;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Commission {
    //수수료 아이디 (고유번호 or 주문번호)
    private String ID;
    //결제일 날짜 (8자리)
    private String date;
    //소분류 -> 수수료
    private String smallCategory;
    //수수료 개수(gruop by 했을 때 확인용)
    private Integer commissionCount;
    //수수료 값
    private Integer commissionAmount;
    //판매채널 (초기화 : 0, 농라매출 : 1, 농라정산:2, 스마트스토어매출:3, 스마트스토어정산4)
    private Integer channel;
}

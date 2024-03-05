package org.example.service;

import org.example.domain.Category;
import org.example.domain.Commission;
import org.example.domain.Sales;
import org.example.repository.CategoryMapper;
import org.example.repository.SalesMapper;
import org.example.util.ExelUtil;
import org.example.util.Parsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesService {

    @Autowired
    private SalesMapper salesRepository;

    @Autowired
    private CategoryMapper categoryReposity;
    //업로드할 파일이 중복된 데이터가 있는지 검수한다
    public Integer checkUploadSales(Sales sales){
        return salesRepository.checkUploadSales(sales).size();
    }

    public Integer checkUploadCommission(Commission commission){
        return salesRepository.checkUploadCommission(commission).size();
    }

    public Boolean uploadNongraSales(MultipartFile multi) throws IOException {
        List<Sales> tmpList = new ArrayList<Sales>(); //임시 리스트
        tmpList = ExelUtil.uploadNongraSales(multi); //리턴할 리스트
        List<Sales> salesList = new ArrayList<Sales>();
        System.out.println(tmpList.toString());

        for(int i = 0; i < tmpList.size(); i++){
            //아이디(주문번호) 받아오기
            String ID = tmpList.get(i).getID();
            System.out.println("ID : " + ID);
            //날짜 추출
            String tmpDate = tmpList.get(i).getDate();
            // 14자리 추출로 수정
            String date = tmpDate.substring(0,4)+tmpDate.substring(5,7)+tmpDate.substring(8,10)
                    +tmpDate.substring(11,13)+tmpDate.substring(14,16)+tmpDate.substring(17,19);
            System.out.println("date : " + date);
            //금액 확인(택배비가 있는지 확인하고 있다면 목록 생성)
            Integer checkAmount = tmpList.get(i).getSalesAmount();
            System.out.println("checkAmount : " + checkAmount);

            String[] productName = tmpList.get(i).getProductName().split("/");
            for(int j = 0; j<productName.length;j++){
                //제품 이름 추출
                String name = productName[j].substring(0,productName[j].indexOf("(")-1);
                System.out.println("name : " + name);
                //제품 개수 추출
                String tmp = productName[j].substring(productName[j].indexOf("(")+1,productName[j].indexOf(")")-2);
                Integer count = Integer.parseInt(tmp);
                System.out.println("count : "+count);
                //제품 소분류 추출
                Map<String,String> nameDate = new HashMap<>();
                nameDate.put("productName", name);

                String date12 = tmpDate.substring(0,4)+tmpDate.substring(5,7)+tmpDate.substring(8,10);
                System.out.println("date12 : "+date12);
                nameDate.put("date", date12); // 여기는 date.substring 0에서 8자리
                Category category = categoryReposity.findSmallCategory(nameDate);
                System.out.println(category.toString());

                //sales 세팅
                Sales sales = new Sales();
                // sales.setdate-14자리 date;
                sales.setID(ID);
                sales.setDate(date);
                sales.setSmallCategory(category.getSmallCategory());
                sales.setChannel(1); //농라매출 :1
                sales.setProductName(name);
                sales.setPayway(tmpList.get(i).getPayway()); //카드 :0, 현금:1
                sales.setSalesCount(count);
                sales.setSalesAmount(category.getProductAmount()*count);
                checkAmount -= category.getProductAmount()*count;

                //중복된 데이터가 있는지 확인하는 함수
                if(checkUploadSales(sales) >0){
                    return false;
                }

                salesList.add(sales);
                System.out.println(sales.toString());
            }
            //고객부담배송비가 있을경우
            if(checkAmount > 0){
                Sales sales = new Sales();
                sales.setID(ID);
                sales.setDate(date);
                sales.setChannel(1);
                sales.setSmallCategory("택배비");
                sales.setProductName("고객부담배송비");
                sales.setSalesCount(1);
                sales.setSalesAmount(checkAmount);
                sales.setPayway(tmpList.get(i).getPayway());

                salesList.add(sales);
                System.out.println(sales.toString());
            }

        }
        salesRepository.uploadNongraSales(salesList);
        return true;

    }

    public Boolean uploadNongraCommission(MultipartFile multi) throws IOException{
        List<Commission> tmpList = new ArrayList<Commission>(); //임시 리스트
        tmpList = ExelUtil.uploadNongraCommission(multi);
        //리턴할 리스트
        List<Commission> commissionList = new ArrayList<Commission>();
        System.out.println(tmpList.toString());

        for(int i = 0; i < tmpList.size(); i++){

            //commission 세팅
            Commission commission = new Commission();
            //아이디(주문번호) 받아오기
            commission.setID(tmpList.get(i).getID());
            //날짜 추출
            String tmpDate = tmpList.get(i).getDate();
            // 8자리 추출로 수정
            String date = tmpDate.substring(0,4)+tmpDate.substring(5,7)+tmpDate.substring(8,10);
            commission.setDate(date);
            //소분류
            commission.setSmallCategory("수수료");
            //수수료 개수(항상 1)
            commission.setCommissionCount(1);
            //수수료 값
            commission.setCommissionAmount(tmpList.get(i).getCommissionAmount());
            //판매채널 (초기화 : 0, 농라 : 1, 스마트스토어:2)
            commission.setChannel(1);

            //같은 데이터가 있는지 체크 없으면 저장
            if(checkUploadCommission(commission)>0){
                return false;
            }

            commissionList.add(commission);
            System.out.println(commission.toString());

        }
        salesRepository.uploadNongraCommission(commissionList);
        return true;
    }

//    public Boolean uploadNongraCal(MultipartFile multi){
//
//    }
//
//    public Boolean uploadSmartstoreSales(MultipartFile multi){
//
//    }
//
//    public Boolean uploadSmartstoreCal(MultipartFile multi){
//
//    }
}

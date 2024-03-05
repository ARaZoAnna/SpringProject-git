package org.example.util;

import java.util.HashMap;

public class Parsing {
    //객체 생성하지 않고 함수를 사용하기위해 static 사용
//    public static HashMap<String,Integer> parseProductName (String productName){
//        //, 기준으로 파싱하여 상품을 개별 추출한다
//        //String[] parseProduct = productName.split(",");
//
//        //HashMap<String,Integer> nameCount = new HashMap<String,Integer>();
//        //for(String str : parseProduct){
//        //상품명을 추출한다
//        String name = str.substring(0,str.indexOf("("));
//        //상품 개수를 추출한다 (0개)
//        String tmp = str.substring(str.indexOf("("),str.indexOf(")")-1);
//            Integer value = Integer.parseInt(tmp);
//            //해쉬맵에 저장한다
//            nameCount.put(key, value);
//        //}
//
//        return nameCount;
//    }
}

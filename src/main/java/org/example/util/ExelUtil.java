package org.example.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.example.domain.Category;
import org.example.domain.Commission;
import org.example.domain.Sales;
import org.example.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExelUtil {
    //농라 매출 엑셀 업로드시 호출하는 함수
    public static List<Sales> uploadNongraSales(MultipartFile multi) throws IOException{
        List<Sales> salesList = new ArrayList<Sales>();

        Workbook workbook = null; //excel 생성
        InputStream fis = null; //파일 데이터 전송하는 통로 생성

        try{
            fis = multi.getInputStream(); //MultipartFile로 파일 데이터를 가져옴
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet = null; //workbook 내에 sheet생성
            XSSFRow curRow = null; //sheet 내에 row 생성
            XSSFCell curCell = null; // row 내에 여러개의 cell생성

            curSheet = (XSSFSheet) workbook.getSheetAt(0); //첫번째 sheet 저장

            for(int row = 1 ; row <= curSheet.getLastRowNum() ; row++) { // 두번째줄부터 마지막줄까지 한줄 씩 읽으면...
                curRow = curSheet.getRow(row);
                Sales sales = new Sales();
                DataFormatter df = new DataFormatter();
                sales.setID(df.formatCellValue(curRow.getCell(0)));
                sales.setDate(df.formatCellValue(curRow.getCell(1)));
                sales.setProductName(df.formatCellValue(curRow.getCell(14)));
                //지불 방법 integer로 변경
                String tmpPayway = df.formatCellValue(curRow.getCell(3));
                Integer payway = 0;
                if (tmpPayway.contains("카드")){
                    payway = 1;
                }
                else if (tmpPayway.contains("현금")){
                    payway = 2;
                }
                sales.setPayway(payway);
                sales.setSalesAmount(Integer.valueOf(df.formatCellValue(curRow.getCell(13))));
               // System.out.println(sales.toString());
                salesList.add(sales);
            }

        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            if(fis != null)
                fis.close();
        }
        return salesList;
    }
    //농라 정산 엑셀 업로드시 호출하는 함수
    public static List<Commission> uploadNongraCommission(MultipartFile multi) throws IOException {

        List<Commission> commissionList = new ArrayList<Commission>();

        Workbook workbook = null; //excel 생성
        InputStream fis = null; //파일 데이터 전송하는 통로 생성

        try{
            fis = multi.getInputStream(); //MultipartFile로 파일 데이터를 가져옴
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet = null; //workbook 내에 sheet생성
            XSSFRow curRow = null; //sheet 내에 row 생성
            XSSFCell curCell = null; // row 내에 여러개의 cell생성

            curSheet = (XSSFSheet) workbook.getSheetAt(0); //첫번째 sheet 저장

            for(int row = 1 ; row <= curSheet.getLastRowNum() ; row++) { // 두번째줄부터 마지막줄까지 한줄 씩 읽으면...
                curRow = curSheet.getRow(row);
                Commission commission = new Commission();
                DataFormatter df = new DataFormatter();
                commission.setID(df.formatCellValue(curRow.getCell(2)));
                commission.setDate(df.formatCellValue(curRow.getCell(9)));
                //수수료 계산하기 tmpAmount : 판매자지불금액, tmpFee : 정산금액
                Integer tmpAmount = Integer.valueOf(df.formatCellValue(curRow.getCell(7)));
                Integer tmpFee = Integer.valueOf(df.formatCellValue(curRow.getCell(8)));
                commission.setCommissionAmount(tmpAmount-tmpFee);

                // System.out.println(sales.toString());
                commissionList.add(commission);
            }

        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            if(fis != null)
                fis.close();
        }
        return commissionList;
    }
}

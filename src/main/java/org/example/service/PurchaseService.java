package org.example.service;

import org.example.domain.Purchase;
import org.example.repository.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseMapper purchaseRepository;
    //매입 정보 입력
    public void save(Purchase purchase){
        purchaseRepository.savePurchase(purchase);
    }
    //매입 정보 리스트 모두 조회
    public List<Purchase> selectAll(){
        return purchaseRepository.selectAll();
    }
    // 특정 달로 매입 정보 리스트 조회
    public List<Purchase> selectByDate(String date){
        return purchaseRepository.selectByDate(date);
    }
    //매입 정보 수정
    public Boolean updatePurchase(Purchase purchase){
        //기존 데이터가 있다면
        if (selectByDateAndSmallCategory(purchase).size()>0){
            purchaseRepository.updatePurchase(purchase);
            return true;
        }
        return false;
    }
    //날짜와 소분류로 데이터가 있는지 확인할 때 사용하는 함수
    public List<Purchase> selectByDateAndSmallCategory(Purchase purchase){
        return purchaseRepository.selectByDateAndSmallCategory(purchase);
    }
    //매입 정보 삭제
    public Boolean deletePurchase(Purchase purchase){
        //기존 데이터가 있다면
        if (selectByDateAndSmallCategory(purchase).size()>0){
            purchaseRepository.deletePurchase(purchase);
            return true;
        }
        return false;
    }
}

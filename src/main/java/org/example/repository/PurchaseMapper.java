package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Purchase;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    void savePurchase(Purchase purchase);

    List<Purchase> selectAll();

    List<Purchase> selectByDate(String inputdate);

    void updatePurchase(Purchase purchase);

    List<Purchase> selectByDateAndSmallCategory(Purchase purchase);

    void deletePurchase(Purchase purchase);



}

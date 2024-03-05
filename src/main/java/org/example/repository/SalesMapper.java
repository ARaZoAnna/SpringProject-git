package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Commission;
import org.example.domain.Sales;

import java.util.List;

@Mapper
public interface SalesMapper {
    void uploadNongraSales(List<Sales> salesList);

    void uploadNongraCommission(List<Commission> commissionList);

    List<Sales> checkUploadSales(Sales sales);

    List<Commission> checkUploadCommission(Commission commission);
}

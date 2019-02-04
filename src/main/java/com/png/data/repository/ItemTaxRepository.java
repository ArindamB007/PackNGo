package com.png.data.repository;

import com.png.data.entity.ItemTax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTaxRepository extends JpaRepository<ItemTax,Integer> {
    List<ItemTax> findAll();

    List<ItemTax> findAllByItemTaxPercentEquals(String taxPercent);
}

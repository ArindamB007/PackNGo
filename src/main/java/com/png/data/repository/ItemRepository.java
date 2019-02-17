package com.png.data.repository;

import com.png.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    Item findItemByDescriptionEquals(String description);
}

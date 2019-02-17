package com.png.data.repository;

import com.png.data.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemRepositoryImpl implements ItemRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void detach(Item item) {
        em.detach(item);
    }
}

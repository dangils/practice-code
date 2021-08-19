package com.koreait.day02.repository;

import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findByContent(String content);
    Optional<Item> findFirstByNameOrderByIdDesc(String name);
    Optional<Item> findFirstByTitleOrderByPriceDesc(String title);

}

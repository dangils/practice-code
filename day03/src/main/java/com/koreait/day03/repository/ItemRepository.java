package com.koreait.day03.repository;

import com.koreait.day03.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findById(Long id);
    Optional<Item> findByContent(String content);
    Optional<Item> findFirstByNameOrderByIdDesc(String name);
    Optional<Item> findFirstByTitleOrderByPriceDesc(String title);

}

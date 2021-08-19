package com.koreait.day02.repository;

import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByType(String type);

    Optional<Category> findFirstByTitleOrderByIdDesc(String title);

    Optional<Category> findFirstByTypeOrderByIdDesc(String type);
}
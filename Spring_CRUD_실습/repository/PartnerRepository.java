package com.koreait.day02.repository;

import com.koreait.day02.model.entity.OrderGroup;
import com.koreait.day02.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    Optional<Partner> findFirstByIdOrderByIdDesc(Long id);
    Optional<Partner> findFirstByNameOrderByIdDesc(String name);
    Optional<Partner> findById(Long id);

}

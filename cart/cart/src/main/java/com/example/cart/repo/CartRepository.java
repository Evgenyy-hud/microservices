package com.example.cart.repo;


import com.example.cart.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartProduct, Long> {

    Optional<CartProduct> findByTovarId(Long tovarId);

    @Modifying
    @Transactional
    @Query("UPDATE CartProduct c SET c.count = c.count + :count WHERE c.tovarId = :tovarId")
    int incrementCount(@Param("tovarId") Long tovarId, @Param("count") Integer count);
}


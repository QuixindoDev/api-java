package com.quixindodev.api_springboot.repositories;

import com.quixindodev.api_springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, UUID> {
}

package com.assessment.SpringBootMicroservicesContainerization.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.SpringBootMicroservicesContainerization.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}

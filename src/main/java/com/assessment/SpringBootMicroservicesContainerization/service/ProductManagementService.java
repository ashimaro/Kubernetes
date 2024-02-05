package com.assessment.SpringBootMicroservicesContainerization.service;

import com.assessment.SpringBootMicroservicesContainerization.dto.request.AddProductRequest;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.AddProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.DeleteProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.GetProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.UpdateProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.entity.Product;
import com.assessment.SpringBootMicroservicesContainerization.exception.CustomErrorException;
import com.assessment.SpringBootMicroservicesContainerization.repo.ProductRepository;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@AllArgsConstructor
public class ProductManagementService {
    //constant
    static final String NOT_FOUND_MESSAGE = "Product not found";
    private ProductRepository repo;
 
    public AddProductResDto addProduct(AddProductRequest dto) {
         log.debug("dto: {}", dto);
        try {
           
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        
     repo.save(product);
     log.debug("Product with ID: {}, name: {}, desc: {}, saved successfully.", product.getId(), product.getName(), product.getDescription());

        return AddProductResDto.builder().build();

    } catch (Exception e) {
     // Log the exception
     log.error("Error while adding product", e);
     e.printStackTrace();
     throw e;  // Ensure the exception is re-thrown to trigger a rollback
    }

    }
    public GetProductResDto getProductById(@PathVariable Integer productId) {
        try {
            Optional<Product> optionalProduct  = repo.findById(productId);

            if (optionalProduct != null) {
                Product product = optionalProduct.get();
                // Convert the retrieved product to GetProductResDto using a mapper or builder
                log.debug("Product ID: {}, name: {}, desc: {}.", product.getId(), product.getName(), product.getDescription());
                return GetProductResDto.builder().productList(Arrays.asList(product)) .build();
            } else {
                // Handle the case where the product with the given ID is not found
                log.warn("Product with ID {} not found.", productId);
              throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
            }
        } catch (Exception e) {
            // Log the exception
            log.error("Error while getting product by ID");
           throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
        }
    }

    public GetProductResDto getAllProducts() {
    try {
        List<Product> productList = repo.findAll();

        if (!productList.isEmpty()) {
            // Convert the list of products to a list of GetProductResDto using a mapper or builder
           GetProductResDto productDtoList = GetProductResDto.builder()
           .productList(productList)
           .build();

            log.debug("Retrieved {} products.", productList.size());
            return productDtoList;
        } else {
            // Handle the case where no products are found
            log.warn("No products found.");
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "404", "No products found.");
        }
    } catch (Exception e) {
        // Log the exception
        log.error("Error while getting all products");
        throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Internal server error");
    }
}
 public UpdateProductResDto updateProduct(Integer productId, AddProductRequest updatedProductData) {
        try {
            // Check if the product with the given ID exists
            Optional<Product> optionalProduct = repo.findById(productId);

            if (optionalProduct.isPresent()) {
                // Update the product data
                Product existingProduct = optionalProduct.get();
                existingProduct.setName(updatedProductData.getName());
                existingProduct.setDescription(updatedProductData.getDescription());

                // Save the updated product
                repo.save(existingProduct);

                log.debug("Product with ID: {} updated successfully.", productId);

                // Construct and return the response DTO
                return UpdateProductResDto.builder().build();
            } else {
                // Handle the case where the product with the given ID is not found
                log.warn("Product with ID {} not found for update.", productId);
                throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
            }
        } catch (Exception e) {
            // Log the exception
            log.error("Error while updating product with ID: {}", productId, e);
           throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
        }
    }
    public DeleteProductResDto deleteProductById(Integer productId) {
        try {
            // Check if the product with the given ID exists
            Optional<Product> optionalProduct = repo.findById(productId);

            if (optionalProduct.isPresent()) {
                // Delete the product
                repo.deleteById(productId);

                log.debug("Product with ID: {} deleted successfully.", productId);

                // Construct and return the response DTO
                return DeleteProductResDto.builder().build();
            } else {
                // Handle the case where the product with the given ID is not found
                log.warn("Product with ID {} not found for deletion.", productId);
                throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
            }
        } catch (Exception e) {
            // Log the exception
            log.error("Error while deleting product with ID: {}", productId, e);
           throw new CustomErrorException(HttpStatus.BAD_REQUEST, "404", NOT_FOUND_MESSAGE);
        }
    }

    }

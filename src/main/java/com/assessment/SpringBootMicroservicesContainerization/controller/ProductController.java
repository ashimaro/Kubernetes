package com.assessment.SpringBootMicroservicesContainerization.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.AddProductRequest;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.AddProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.DeleteProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.GetProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.dto.request.response.UpdateProductResDto;
import com.assessment.SpringBootMicroservicesContainerization.service.ProductManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {
private ProductManagementService productManagementService;

        @PostMapping("/products")
    public ResponseEntity<AddProductResDto> addProduct(@RequestBody AddProductRequest request) {
          log.debug("AddProductRequest: {}", request);
    return ResponseEntity.ok(productManagementService.addProduct(request));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<GetProductResDto>getProductById(@PathVariable Integer productId) {
         log.debug("productId: {}", productId);
        return ResponseEntity.ok(productManagementService.getProductById(productId));
    }

    @GetMapping("/products")
public ResponseEntity <GetProductResDto> getAllProducts() {
    return ResponseEntity.ok(productManagementService.getAllProducts());
}

@PutMapping("/products/{productId}")
public ResponseEntity <UpdateProductResDto> updateProduct(@PathVariable Integer productId,@RequestBody AddProductRequest request){
    return ResponseEntity.ok(productManagementService.updateProduct(productId , request));
}

@DeleteMapping("/products/{productId}")
public ResponseEntity<DeleteProductResDto>deleteProductById(@PathVariable Integer productId) {
         log.debug("productId: {}", productId);
        return ResponseEntity.ok(productManagementService.deleteProductById(productId));
    }




    


    
}

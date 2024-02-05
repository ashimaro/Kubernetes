package com.assessment.SpringBootMicroservicesContainerization.dto.request.response;

import java.util.List;

import com.assessment.SpringBootMicroservicesContainerization.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductResDto {
    @Builder.Default
    private String statusCode="200";
    @Builder.Default
    private String statusMessage="Successful operation";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Product> productList;
    
}

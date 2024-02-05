package com.assessment.SpringBootMicroservicesContainerization.dto.request.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProductResDto {
    @Builder.Default
    private String statusCode="201";
    @Builder.Default
    private String statusMessage="Product created successfully";
    
}

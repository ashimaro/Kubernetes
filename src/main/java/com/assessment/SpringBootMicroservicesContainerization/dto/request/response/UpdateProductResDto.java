package com.assessment.SpringBootMicroservicesContainerization.dto.request.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductResDto {
       @Builder.Default
    private String statusCode="200";
    @Builder.Default
    private String statusMessage="Product updated successfully";
}

package com.assessment.SpringBootMicroservicesContainerization.dto.request.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteProductResDto {
     @Builder.Default
    private String statusCode="204";
    @Builder.Default
    private String statusMessage="Product deleted successfully";
}

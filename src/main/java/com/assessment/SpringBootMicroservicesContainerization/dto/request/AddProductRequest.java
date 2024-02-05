package com.assessment.SpringBootMicroservicesContainerization.dto.request;
import lombok.Data;
@Data
public class AddProductRequest {
    private int id;
    private String name;
    private String description;
}

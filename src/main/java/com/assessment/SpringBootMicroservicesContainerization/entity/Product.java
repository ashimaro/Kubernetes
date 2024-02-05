package com.assessment.SpringBootMicroservicesContainerization.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "assessment")
@Builder
@Data
@AllArgsConstructor
public class Product{
    public Product() {
        //Empty
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}

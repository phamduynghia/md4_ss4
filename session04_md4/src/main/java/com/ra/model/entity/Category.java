package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name = "category_name",nullable = false,unique = true,length = 100)
    private String categoryName;
@Column(name = "description",columnDefinition = "text")
    private String description;

private Boolean status;

}

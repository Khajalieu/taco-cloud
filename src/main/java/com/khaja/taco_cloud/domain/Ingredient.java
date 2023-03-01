package com.khaja.taco_cloud.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    private String id;
    @NotBlank(message = "Ingredient name must not be blank")
    private String name;
    @NotNull(message = "Ingredient type must be specified")
    private Type type;

    public enum Type {
        SAUCE, WRAP, VEGGIES, PROTEIN, CHEESE
    }
}

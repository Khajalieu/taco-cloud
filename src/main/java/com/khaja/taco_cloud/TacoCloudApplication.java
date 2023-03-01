package com.khaja.taco_cloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.khaja.taco_cloud.domain.Ingredient;
import com.khaja.taco_cloud.domain.Ingredient.Type;
import com.khaja.taco_cloud.repository.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	CommandLineRunner loadIngredients(IngredientRepository ingredientsitory) {
		return args -> {
			ingredientsitory.deleteAll();

			ingredientsitory.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			ingredientsitory.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientsitory.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientsitory.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientsitory.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientsitory.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientsitory.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientsitory.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			ingredientsitory.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientsitory.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};

	}

}

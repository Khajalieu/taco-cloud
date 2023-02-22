package com.khaja.tacocloud.ingredient.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khaja.tacocloud.ingredient.model.Ingredient;
import com.khaja.tacocloud.ingredient.model.Ingredient.Type;

@Repository
public class JdbcIngredientRepositoryImpl implements JdbcIngredientRepository {
        private Map<String, Ingredient> ingredientMap = new HashMap<>();

        private JdbcTemplate jdbcTemplate;

        public JdbcIngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        public Map<String, Ingredient> loadIngredients() {
                ingredientMap.put("FLTO",
                                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                ingredientMap.put("COTO",
                                new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
                ingredientMap.put("GRBF",
                                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                ingredientMap.put("CARN",
                                new Ingredient("CARN", "Carnitas", Type.PROTEIN));
                ingredientMap.put("TMTO",
                                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
                ingredientMap.put("LETC",
                                new Ingredient("LETC", "Lettuce", Type.VEGGIES));
                ingredientMap.put("CHED",
                                new Ingredient("CHED", "Cheddar", Type.CHEESE));
                ingredientMap.put("JACK",
                                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
                ingredientMap.put("SLSA",
                                new Ingredient("SLSA", "Salsa", Type.SAUCE));
                ingredientMap.put("SRCR",
                                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

                return ingredientMap;
        }

        @Override
        public Iterable<Ingredient> findAll() {

                return jdbcTemplate.query("select id, name, type from Ingredient",
                                this::mapRowToIngredient);
        }

        @Override
        public Optional<Ingredient> findById(String id) {
                List<Ingredient> results = jdbcTemplate.query(
                                "select id, name, type from Ingredient where id=?",
                                this::mapRowToIngredient, id);
                return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
        }

        @Override
        public Ingredient save(Ingredient ingredient) {
                jdbcTemplate.update(
                                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                                ingredient.getId(),
                                ingredient.getName(),
                                ingredient.getType().toString());
                return ingredient;
        }

        private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
                        throws SQLException {

                return new Ingredient(
                                row.getString("id"),
                                row.getString("name"),
                                Ingredient.Type.valueOf(row.getString("type"))

                );

        }
}

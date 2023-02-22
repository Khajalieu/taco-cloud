package com.khaja.tacocloud.tacoorder.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khaja.tacocloud.tacoorder.model.IngredientRef;
import com.khaja.tacocloud.tacoorder.model.Taco;
import com.khaja.tacocloud.tacoorder.model.TacoOrder;

@Repository
public class TacoOrderRepositoryImpl implements TacoOrderRepository {

    private JdbcOperations jdbcOperations;

    public TacoOrderRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco_Order " + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDelivery().getDeliveryName(),
                        tacoOrder.getDelivery().getDeliveryStreet(),
                        tacoOrder.getDelivery().getDeliveryCity(),
                        tacoOrder.getDelivery().getDeliveryState(),
                        tacoOrder.getDelivery().getDeliveryZip(),
                        tacoOrder.getCreditCard().getCcNumber(),
                        tacoOrder.getCreditCard().getCcExpiration(),
                        tacoOrder.getCreditCard().getCcCVV(),
                        tacoOrder.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        tacoOrder.setId(orderId);
        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return tacoOrder;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco "
                        + "(name, created_at, taco_order, taco_order_key) "
                        + "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCreatedAt(),
                        orderId,
                        orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }

    private void saveIngredientRefs(
            long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), tacoId, key++);
        }
    }

}

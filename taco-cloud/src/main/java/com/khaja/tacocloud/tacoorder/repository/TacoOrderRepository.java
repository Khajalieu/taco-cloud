package com.khaja.tacocloud.tacoorder.repository;

import com.khaja.tacocloud.tacoorder.model.TacoOrder;

public interface TacoOrderRepository {
    
    TacoOrder save(TacoOrder tacoOrder);
}

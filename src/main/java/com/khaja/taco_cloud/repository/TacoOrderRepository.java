package com.khaja.taco_cloud.repository;

import org.springframework.data.repository.CrudRepository;

import com.khaja.taco_cloud.domain.TacoOrder;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, String>{

}

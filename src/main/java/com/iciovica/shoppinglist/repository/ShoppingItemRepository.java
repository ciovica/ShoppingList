package com.iciovica.shoppinglist.repository;

import org.springframework.data.repository.CrudRepository;

import com.iciovica.shoppinglist.model.ShoppingItem;

public interface ShoppingItemRepository extends CrudRepository<ShoppingItem, Long> {

}

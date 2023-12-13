package com.iciovica.shoppinglist.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.iciovica.shoppinglist.repository.ShoppingItemRepository;

public class ShoppingHardcodedData {
	/* treaba asta pare ca nu functioneaza */
	@Autowired
	ShoppingItemRepository shoppingItemRepository;

	public void hardcodedData() {

		if (shoppingItemRepository.count() == 0) {
			ShoppingItem ShoppingItem1 = new ShoppingItem("Coffee");
			ShoppingItem ShoppingItem2 = new ShoppingItem("Milk");

			shoppingItemRepository.save(ShoppingItem1);
			shoppingItemRepository.save(ShoppingItem2);
		}

	}
}
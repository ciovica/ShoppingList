package com.iciovica.shoppinglist.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iciovica.shoppinglist.model.ShoppingItem;
import com.iciovica.shoppinglist.repository.ShoppingItemRepository;

import jakarta.validation.Valid;

@Controller
public class ShoppingItemController {
	@Autowired
	private ShoppingItemRepository shoppingItemRepository;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("shoppingItems", shoppingItemRepository.findAll());
		return model;
	}

	@PostMapping("/shopping")
	public String createShoppingItem(@Valid ShoppingItem shoppingItem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-shopping-item";
		} else {
			shoppingItem.setCreatedDate(LocalDateTime.now());
			shoppingItem.setModifiedDate(LocalDateTime.now());
			shoppingItemRepository.save(shoppingItem);
			return "redirect:/";
		}
	}

	@PostMapping("/shopping/{id}")
	public String updateShoppingItem(@PathVariable("id") long id, @Valid ShoppingItem shoppingItem,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			shoppingItem.setId(id);
			return "update-shopping-item";
		} else {
			shoppingItem.setModifiedDate(LocalDateTime.now());
			shoppingItemRepository.save(shoppingItem);
			return "redirect:/";
		}
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		ShoppingItem shoppingItem = shoppingItemRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ShoppingItem id: " + id + " not found"));
		model.addAttribute("shopping", shoppingItem);
		return "update-shopping-item";
	}

	@GetMapping("/create-shopping")
	public String showCreateForm(ShoppingItem shoppingitem) {
		return "add-shopping-item";
	}

	@GetMapping("/delete/{id}")
	public String deleteShoppingItem(@PathVariable("id") long id, Model model) {
		ShoppingItem shoppingItem = shoppingItemRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ShoppingItem id: " + id + " not found"));
		shoppingItemRepository.delete(shoppingItem);
		return "redirect:/";
	}

}

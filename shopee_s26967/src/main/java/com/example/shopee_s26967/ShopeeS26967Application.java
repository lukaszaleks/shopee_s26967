package com.example.shopee_s26967;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopeeS26967Application {

	public static void main(String[] args) {
		SpringApplication.run(ShopeeS26967Application.class, args);
		execute();
	}


	static void execute() {
		ShopStorage shopStorage = new ShopStorage();
		ShopService shopService = new ShopService(shopStorage);
		Customer customer = new Customer(1, 1500d);
		ShoppingCart shoppingCart = new ShoppingCart(customer);
		shoppingCart.addItem("milk");
		shoppingCart.addItem("milk");
		shoppingCart.addItem("beer");
		shoppingCart.addItem("dupa");
		shopService.orderItems(shoppingCart);
	}

}

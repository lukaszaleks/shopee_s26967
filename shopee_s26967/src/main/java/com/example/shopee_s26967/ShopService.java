package com.example.shopee_s26967;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopStorage shopStorage;

    public ShopService(ShopStorage shopStorage) {
        this.shopStorage = shopStorage;
    }

    public ShoppingCart orderItems(ShoppingCart shoppingCart) {
        double priceToPay = 0d;
        int count = 0;
        List<String> itemNames = shoppingCart.getItemNames();
        for (String itemName : itemNames) {
            Integer itemAmount = itemAmount(itemName);
            Double itemPrice = itemPrice(itemName);
            if (itemAmount != null && itemPrice != null) {
                itemAmount = itemAmount - 1;
                count += 1;
                shopStorage.getStorageItems().put(itemName, itemAmount);
            }
            if (itemPrice != null) {
                priceToPay += count * itemPrice;
                count = 0;
            }
        }
        if (shoppingCart.getCustomer().getBalance() >= priceToPay) {
            shoppingCart.getCustomer().setBalance(purchasePrice(shoppingCart, priceToPay));
            System.out.println("Udało się kupić");
            return shoppingCart;
        } else {
            throw new RuntimeException("Zakup się nie powiódł, masz za mało na koncie");
        }
    }

    public Integer itemAmount(String itemName) {
        return shopStorage.getStorageItems().get(itemName);
    }

    public Double itemPrice(String itemName) {
        return shopStorage.getStorageItemPrices().get(itemName);
    }

    public Double purchasePrice(ShoppingCart shoppingCart, Double price) {
        double balance = shoppingCart.getCustomer().getBalance();
        if (balance - price <= 0) {
            throw new RuntimeException("Kwota nie może być poniżej 0");
        }
        return balance - price;
    }

}

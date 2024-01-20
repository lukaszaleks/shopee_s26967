package com.example.shopee_s26967;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShopStorage {
    private Map<String, Integer> storageItems;
    private Map<String, Double> storageItemPrices;

    public ShopStorage() {
        this.storageItems = new HashMap<>();
        this.storageItemPrices = new HashMap<>();
        this.storageItems.put("milk", 5);
        this.storageItems.put("beer", 2);
        this.storageItemPrices.put("milk", 50d);
        this.storageItemPrices.put("beer", 100d);
    }

    public Map<String, Integer> getStorageItems() {
        return storageItems;
    }

    public Map<String, Double> getStorageItemPrices() {
        return storageItemPrices;
    }
}

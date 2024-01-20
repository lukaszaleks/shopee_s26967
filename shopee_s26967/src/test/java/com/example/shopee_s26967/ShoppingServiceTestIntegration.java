package com.example.shopee_s26967;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingServiceTestIntegration {

    @MockBean
    private ShopStorage shopStorage;

    @Autowired
    private ShopService shopService;

    @Test
    void orderItems_success() {
        //given
        Map<String, Integer> mapItems = new HashMap<>();
        mapItems.put("milk", 1);
        Map<String, Double> mapPrices = new HashMap<>();
        mapPrices.put("milk", 500d);
        Customer customer = new Customer(1, 1000d);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addItem("milk");
        //when
        when(shopStorage.getStorageItems()).thenReturn(mapItems);
        when(shopStorage.getStorageItemPrices()).thenReturn(mapPrices);
        ShoppingCart orderedCart = shopService.orderItems(shoppingCart);
        //then
        assertEquals(500d, orderedCart.getCustomer().getBalance());
    }
}

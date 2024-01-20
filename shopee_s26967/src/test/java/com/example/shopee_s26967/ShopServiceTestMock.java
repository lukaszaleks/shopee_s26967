package com.example.shopee_s26967;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopServiceTestMock {

    @Mock
    private ShopStorage shopStorage;

    @InjectMocks
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

    @Test
    void orderItems_withNotExistedItem_success() {
        //given
        Map<String, Integer> mapItems = new HashMap<>();
        mapItems.put("milk", 1);
        Map<String, Double> mapPrices = new HashMap<>();
        mapPrices.put("milk", 500d);
        Customer customer = new Customer(1, 1000d);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addItem("milk");
        shoppingCart.addItem("dupa");
        //when
        when(shopStorage.getStorageItems()).thenReturn(mapItems);
        when(shopStorage.getStorageItemPrices()).thenReturn(mapPrices);
        ShoppingCart orderedCart = shopService.orderItems(shoppingCart);
        //then
        assertEquals(500d, orderedCart.getCustomer().getBalance());
    }

    @Test
    void orderItems_exception() {
        //given
        Map<String, Integer> mapItems = new HashMap<>();
        mapItems.put("milk", 1);
        Map<String, Double> mapPrices = new HashMap<>();
        mapPrices.put("milk", 500d);
        Customer customer = new Customer(1, 400d);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addItem("milk");
        //when
        when(shopStorage.getStorageItems()).thenReturn(mapItems);
        when(shopStorage.getStorageItemPrices()).thenReturn(mapPrices);
        //then
        assertThrows(RuntimeException.class, () -> shopService.orderItems(shoppingCart));
    }

    @Test
    void itemAmount_success() {
        //given
        Map<String, Integer> mapItems = new HashMap<>();
        mapItems.put("milk", 1);
        //when
        when(shopStorage.getStorageItems()).thenReturn(mapItems);
        Integer amount = shopService.itemAmount("milk");
        //then
        assertEquals(1, amount);
    }

    @Test
    void itemAmount_null() {
        //given
        Map<String, Integer> mapItems = new HashMap<>();
        //when
        when(shopStorage.getStorageItems()).thenReturn(mapItems);
        Integer amount = shopService.itemAmount("milk");
        //then
        assertNull(amount);
    }

    @Test
    void itemPrice_success() {
        //given
        Map<String, Double> mapPrices = new HashMap<>();
        mapPrices.put("milk", 100d);
        //when
        when(shopStorage.getStorageItemPrices()).thenReturn(mapPrices);
        Double price = shopService.itemPrice("milk");
        //then
        assertEquals(100d, price);
    }

    @Test
    void itemPrice_null() {
        //given
        Map<String, Double> mapPrices = new HashMap<>();
        //when
        when(shopStorage.getStorageItemPrices()).thenReturn(mapPrices);
        Double price = shopService.itemPrice("milk");
        //then
        assertNull(price);
    }

    @Test
    void purchasePrice_success() {
        //given
        Customer customer = new Customer(1, 1000d);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        //when
        Double price = shopService.purchasePrice(shoppingCart, 500d);
        //then
        assertEquals(500d, price);
    }

    @Test
    void purchasePrice_exception() {
        //given
        Customer customer = new Customer(1, 400d);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        //when
        //then
        assertThrows(RuntimeException.class, () -> shopService.purchasePrice(shoppingCart, 500d));
    }
}
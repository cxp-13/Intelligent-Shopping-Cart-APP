package com.example.intelligent_shopping_cart.logic.utils

import com.example.intelligent_shopping_cart.model.Commodity

object CommodityFactory {
    private val names = arrayOf("Apple", "Banana", "Orange", "Mango", "Pear")
    private val brands = arrayOf("BrandA", "BrandB", "BrandC")
    private val types = arrayOf("Fruit", "Vegetable", "Snack")

    fun createDummyCommodities(count: Int): List<Commodity> {
        return (1..count).map { index ->
            Commodity(
                name = "${names.random()} $index",
                img = "https://picsum.photos/300?random=$index",
                price = (10..100).random(),
                origin = "Origin $index",
                brand = brands.random(),
                specification = "Specification $index",
                shelfLife = "2023-12-31",
                description = "Description $index",
                count = (0..10).random(),
                type = types.random()
            )
        }
    }
}
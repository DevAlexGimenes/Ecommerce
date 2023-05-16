package com.example.ecommerce.presentation

import com.example.ecommerce.domain.model.CartItem

class CartItemUIModel(
    cartItem: CartItem
) {

    //TODO: "change this CartItemUIModel"
    val priceFormatted: String = "R\$ ${cartItem.price}"
    val quantityInString: String = cartItem.quantity.toString()
}
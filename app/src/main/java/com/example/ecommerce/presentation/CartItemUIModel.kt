package com.example.ecommerce.presentation

import com.example.ecommerce.domain.model.CartItem

class CartItemUIModel(
    cartItem: CartItem
) {

    val priceFormatted: String = "R\$ ${cartItem.price}"
    val quantityInString: String = cartItem.quantity.toString()
}
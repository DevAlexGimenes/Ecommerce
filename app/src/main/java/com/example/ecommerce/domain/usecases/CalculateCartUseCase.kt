package com.example.ecommerce.domain.usecases

import com.example.ecommerce.domain.model.CartItem

class CalculateCartUseCase {

    operator fun invoke(cartItems: List<CartItem>) : Double {
        return cartItems
            .map { it.price * it.quantity }
            .reduce { total, item -> total + item }
//        var cartTotal = 0.0
//        cartItems.forEach {
//            cartTotal =+ it.quantity * it.price
//        }
//        return cartTotal
    }
}
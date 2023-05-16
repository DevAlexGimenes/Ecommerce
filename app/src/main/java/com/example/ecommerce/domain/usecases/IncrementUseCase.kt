package com.example.ecommerce.domain.usecases

import com.example.ecommerce.domain.model.CartItem

class IncrementUseCase {

    operator fun invoke(cartItem: CartItem) : CartItem {
        return cartItem.copy(quantity = cartItem.quantity + 1)
        //return CartItem(cartItem.id, cartItem.name, cartItem.price, cartItem.quantity-1)
    }
}
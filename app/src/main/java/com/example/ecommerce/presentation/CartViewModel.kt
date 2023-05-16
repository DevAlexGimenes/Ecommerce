package com.example.ecommerce.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.ecommerce.domain.model.CartItem
import com.example.ecommerce.domain.usecases.CalculateCartUseCase
import com.example.ecommerce.domain.usecases.DecrementUseCase
import com.example.ecommerce.domain.usecases.IncrementUseCase

class CartViewModel(
    private val decrementUseCase: DecrementUseCase,
    private val incrementUseCase: IncrementUseCase,
    private val calculateCartUseCase: CalculateCartUseCase
) : ViewModel() {

    private val _items = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>>
        get() = _items

    val totalCart: LiveData<Double> = _items.map {
        calculateCartUseCase.invoke(it)
    }

    fun increment(item: CartItem, position: Int) {
        _items.value?.toMutableList()?.let {
            it[position] = incrementUseCase(item)
            _items.postValue(it)
        }
    }

    fun decrement(item: CartItem, position: Int) {
        _items.value?.toMutableList()?.let {
            it[position] = decrementUseCase(item)
            _items.postValue(it)
        }
    }

    fun fetchCartItems() {
        val cartItems = listOf(
            CartItem(
                id = 1,
                name = "Tênis",
                price = 400.0,
                quantity = 1,
            ),
            CartItem(
                id = 2,
                name = "Camiseta",
                price = 100.0,
                quantity = 1,
            )
        )

        _items.postValue(cartItems)
    }
}
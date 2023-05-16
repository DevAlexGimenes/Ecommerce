package com.example.ecommerce.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ecommerce.databinding.ActivityMainBinding
import com.example.ecommerce.domain.model.CartItem
import com.example.ecommerce.domain.usecases.CalculateCartUseCase
import com.example.ecommerce.domain.usecases.DecrementUseCase
import com.example.ecommerce.domain.usecases.IncrementUseCase

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = CartViewModel(DecrementUseCase(), IncrementUseCase(), CalculateCartUseCase())
    private val adapter = CartAdapter(
        viewModel::increment,
        viewModel::decrement
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViews()
        fetchCartItems()
        bindObservers()
    }

    private fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.productsList.adapter = adapter
    }

    private fun fetchCartItems() {
        viewModel.fetchCartItems()
    }

    private fun bindObservers() {
        viewModel.items.observe(this) { cartItems -> adapter.setCartList(cartItems) }

        viewModel.totalCart.observe(this) { totalOfValueInEcommerce -> binding.totalValue.text = totalOfValueInEcommerce.toString() }
    }
}
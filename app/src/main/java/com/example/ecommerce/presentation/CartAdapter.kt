package com.example.ecommerce.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.databinding.ListItemCartBinding
import com.example.ecommerce.domain.model.CartItem

class CartAdapter(
    private val incrementFunction: (CartItem, Int) -> Unit,
    private val decrementFunction: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    private val cartItems = mutableListOf<CartItem>()

    class DiffCallback(
        private val oldList: List<CartItem>,
        private val newList: List<CartItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition].name == newList[newItemPosition].name) &&
                    (oldList[oldItemPosition].price == newList[newItemPosition].price) &&
                        (oldList[oldItemPosition].quantity == newList[newItemPosition].quantity)
        }
    }

    inner class CartItemViewHolder(private val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCartItem(item: CartItem, position: Int) = with(binding) {
            binding.apply {
                incrementButton.setOnClickListener {
                    incrementFunction(item, position)
                }
                decrementButton.setOnClickListener {
                    decrementFunction(item, position)
                }
            }
            this.viewModel = CartItemUIModel(item)
            this.item= item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCartBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bindCartItem(cartItem, position)
    }

    fun setCartList(cartList: List<CartItem>) {
        cartItems.apply {
            val diffResult = DiffUtil.calculateDiff(DiffCallback(this, cartList))
            clear()
            addAll(cartList)
            diffResult.dispatchUpdatesTo(this@CartAdapter)
        }
    }
}
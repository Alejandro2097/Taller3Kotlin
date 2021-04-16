package com.example.tallerf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tallerf.R
import com.example.tallerf.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(val products: MutableList<Product>, val callback:(Product, Boolean) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View, val callback:(Product, Boolean) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            itemView.nameTextview.text = item.name
            itemView.descriptionTextview.text = item.descripcion
            itemView.stockTextview.text = item.stock.toString()
            itemView.deleteButom.setOnClickListener {
                callback(item, true)
            }
            itemView.editButom.setOnClickListener {
                callback(item, false)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, callback)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
    fun addProduct(product: Product) {
        products.add(product)
    }

    fun deleteProduct(product: Product) {
        products.remove(product)
    }

    fun editProduct(product: Product, newName: String, newDescription: String) {
        val index = products.indexOf(product)
        products[index].name = newName
        products[index].descripcion = newDescription
    }

}
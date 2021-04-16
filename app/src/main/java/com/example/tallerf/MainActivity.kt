package com.example.tallerf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tallerf.adapter.ProductAdapter
import com.example.tallerf.dialogs.ProductDialog
import com.example.tallerf.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Lateinit me permite asegurarle a
    // Android que esta variable sera inicializada más adelante
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
        setupList()
    }

    private fun setupButtons() {
        addButton.setOnClickListener {
            val dialog = ProductDialog(this, "", "") { name, description ->
                addProduct(name, description)
            }
            dialog.show()
        }
    }

    private fun setupList() {
        val products = mutableListOf(Product("Producto 1", "XX", 2),
            Product("Producto 2", "ASDF adsgdasgasdgasdgsadg", 5))

        adapter = ProductAdapter(products) { item, isDelete ->
            if(isDelete) deleteProduct(item)
            else editProduct(item)
        }


        productRecyclerView.adapter = adapter
        productRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun addProduct(name: String, description: String) {
        val product = Product(name, description, 1)
        adapter.addProduct(product)
        adapter.notifyDataSetChanged()
    }
    private fun deleteProduct(product: Product) {
        
        //Crear una confirmación
        adapter.deleteProduct(product)
        adapter.notifyDataSetChanged()
    }
    private fun updateProduct(product: Product, name: String, description: String) {
        adapter.editProduct(product, name, description)
        adapter.notifyDataSetChanged()
    }

    private fun editProduct(product: Product) {
        val dialog = ProductDialog(this, product.name, product.descripcion) { name, description ->
            updateProduct(product, name, description)
        }
        dialog.show()
    }

}

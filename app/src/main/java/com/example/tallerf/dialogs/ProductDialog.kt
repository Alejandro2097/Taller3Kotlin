package com.example.tallerf.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.tallerf.R
import kotlinx.android.synthetic.main.dialog_product.*

class ProductDialog(context: Context, val name: String, val description: String, private val callback: (String, String) -> Unit) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product)
        nameEditText.setText(name)
        descriptionEditText.setText(description)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            callback(name, description)
            dismiss()
        }
    }
}

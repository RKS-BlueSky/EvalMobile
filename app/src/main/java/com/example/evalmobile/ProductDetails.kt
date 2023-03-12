package com.example.evalmobile

import android.os.Bundle

class ProductDetails : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setHeaderTitle(getString(R.string.main_button_produits))
    }
}
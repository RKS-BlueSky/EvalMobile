package com.example.evalmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle(getString(R.string.main_title))

        setHeaderTitle(getString(R.string.main_title))

        val button=findViewById<Button>(R.id.buttonInfos)
        button.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, GroupInfos::class.java)
            startActivity(newIntent)
        })

        val buttonEspace=findViewById<Button>(R.id.buttonProduits)
        buttonEspace.setOnClickListener(View.OnClickListener {val newIntent = Intent(application, CategoriesActivity::class.java)
            startActivity(newIntent)
        })
    }
}
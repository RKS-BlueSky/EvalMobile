package com.example.evalmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ProductsListActivity : AppCompatActivity() {
//    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        val link = intent.getStringExtra("link")

        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(link.toString())
                .build()

            val response = client.newCall(request).execute()

            runOnUiThread {
                val responseBody = response.body?.string()
                val products = arrayListOf<Product>()
                val jsProducts = JSONObject(responseBody.toString())
                val jsArrayProducts = jsProducts.getJSONArray("items")
                for(i in 0 until jsArrayProducts.length()){
                    val js=jsArrayProducts.getJSONObject(i)
                    val product=Product(js.optString("name","not found"),
                        js.optString("description","not found"),
                        js.optString("picture_url","not found"))
                    products.add(product)
                }
            }
        }
    }
}
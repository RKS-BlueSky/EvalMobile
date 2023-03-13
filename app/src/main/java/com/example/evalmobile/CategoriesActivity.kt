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
import org.json.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request

class CategoriesActivity : BaseActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catgeories)
        showBack()
        setHeaderTitle("Rayons")

            GlobalScope.launch(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.ugarit.online/epsi/categories.json")
                    .build()

                val response = client.newCall(request).execute()

                runOnUiThread {
                    val responseBody = response.body?.string()
                    val items = arrayListOf<Item>()
                    val jsItems = JSONObject(responseBody.toString())
                    val jsArrayItems = jsItems.getJSONArray("items")
                    for(i in 0 until jsArrayItems.length()){
                        val js=jsArrayItems.getJSONObject(i)
                        val item=Item(js.optString("category_id","not found"),
                            js.optString("title","not found"),
                            js.optString("products_url","not found"))
                        items.add(item)
                    }
                    val buttonLayout = findViewById<LinearLayout>(R.id.button_layout)

                    items.forEach { item ->
                        val button = Button(this@CategoriesActivity)
                        button.text = item.title
                        button.setOnClickListener {
                            val intent = Intent(this@CategoriesActivity, ProductsListActivity::class.java)
                            intent.putExtra("link", item.products_url)
                            intent.putExtra("title", item.title)
                            startActivity(intent)
                        }
                        buttonLayout.addView(button)
                    }
                }
            }
        }
    }
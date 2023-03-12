package com.example.evalmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catgeories)
        val url = URL("https://example.com/api/data")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val response = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                response.append(line)
            }
            bufferedReader.close()

            val responseData = response.toString()

            val items = arrayListOf<Item>()
            val jsItems = JSONObject(responseData)
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
                val button = Button(this)
                button.text = item.title
                button.setOnClickListener {
                    val intent = Intent(this, ProductsListActivity::class.java)
                    intent.putExtra("link", item.products_url)
                    startActivity(intent)
                }
                buttonLayout.addView(button)
            }
        }
    }
}
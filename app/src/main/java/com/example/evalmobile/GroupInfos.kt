package com.example.evalmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class GroupInfos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_infos)

        val luc = Student("Dutheil", "Luc", "john.doe@example.com", "NCL", "Betclic dev")
        val nathan = Student("Tomasian", "Nathan", "nathantomasian@gmaiL.com", "NCL", "Betclic dev")
        val clement = Student("Clement", "Temple", "clemtmpl@gmail.com", "NCL", "Vistalid dev")

        val studentList = arrayListOf(luc,nathan,clement)

        // Set up the buttons
        val buttonLayout = findViewById<LinearLayout>(R.id.button_layout)

        studentList.forEach { student ->
            val button = Button(this)
            button.text = student.firstName
            button.setOnClickListener {
                val intent = Intent(this, GroupInfosActivity::class.java)
                intent.putExtra("student", "test")
                startActivity(intent)
            }
            buttonLayout.addView(button)
        }
    }
}
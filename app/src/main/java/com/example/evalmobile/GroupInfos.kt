package com.example.evalmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class GroupInfos : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_infos)
        setHeaderTitle(getString(R.string.main_button_infos))
        showBack()

        val luc = Student("Dutheil", "Luc", "john.doe@example.com", "NCL", "Betclic dev", 0)
        val nathan = Student("Tomasian", "Nathan", "nathantomasian@gmaiL.com", "NCL", "Betclic dev",1)
        val clement = Student("Clement", "Temple", "clemtmpl@gmail.com", "NCL", "Vistalid dev", 2)

        val studentList = arrayListOf(luc,nathan,clement)

        // Set up the buttons
        val buttonLayout = findViewById<LinearLayout>(R.id.button_layout)

        studentList.forEach { student ->
            val button = Button(this)
            button.text = student.firstName
            button.setOnClickListener {
                val intent = Intent(this, StudentInfosActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            }
            buttonLayout.addView(button)
        }
    }
}
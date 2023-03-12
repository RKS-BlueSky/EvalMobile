package com.example.evalmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class StudentInfosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_info)
        val student = intent.getParcelableExtra<Student>("student")
        val textEmail = findViewById<TextView>(R.id.textEmail)
        val textEpsi = findViewById<TextView>(R.id.textEpsi)
        val textName = findViewById<TextView>(R.id.textName)
        val textInfos = findViewById<TextView>(R.id.textInfoStudent)
        val textGroupe = findViewById<TextView>(R.id.textGroupe)

        if (student != null) {
            textEmail.text = student.email
            textGroupe.text = student.groupe
            textEpsi.text = "https://www.epsi.fr/"
            textInfos.text = student.info
            textName.text = "${student.firstName}${student.lastName}"
        }


    }
}
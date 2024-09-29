package com.siaptekno.myintentapp

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveWithObjectActivy : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object_activy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val objectTextView: TextView = findViewById(R.id.tv_object_received)

        val person = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("extra_person", Person::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("extra_person")
        }

        if (person != null) {
            val text = "Name : ${person.name.toString()},\nEmail : ${person.email},\nLocation : ${person.city}"
            objectTextView.text = text
        }
    }

}
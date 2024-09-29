package com.siaptekno.myintentapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val moveActivityButton = findViewById<Button>(R.id.moveActivityButton)
        moveActivityButton.setOnClickListener(this)
        val moveActivityWithDataButton = findViewById<Button>(R.id.moveActivityWithDataButton)
        moveActivityWithDataButton.setOnClickListener(this)
        val moveActivityObjectButton = findViewById<Button>(R.id.moveActivityObjectButton)
        moveActivityObjectButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.moveActivityButton -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
                }

            R.id.moveActivityWithDataButton -> {
                val moveWithDataActivity = Intent(this, MoveWithDataActivity::class.java)
                moveWithDataActivity.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy")
                moveWithDataActivity.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataActivity)
            }
            R.id.moveActivityObjectButton -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )

                val moveWithObjectActivity = Intent(this@MainActivity, MoveWithObjectActivy::class.java)
                moveWithObjectActivity.putExtra(MoveWithObjectActivy.EXTRA_PERSON, person)
                startActivity(moveWithObjectActivity)
            }
        }
    }
}
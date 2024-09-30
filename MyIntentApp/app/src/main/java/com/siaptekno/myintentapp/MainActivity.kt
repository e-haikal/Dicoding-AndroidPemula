package com.siaptekno.myintentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

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
        val dialNumberButton = findViewById<Button>(R.id.dialNumberButton)
        dialNumberButton.setOnClickListener(this)
        val btnMoveForResult = findViewById<Button>(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
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
            R.id.dialNumberButton -> {
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultActivity = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultActivity)
            }
        }
    }
}
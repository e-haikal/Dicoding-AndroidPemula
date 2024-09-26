package com.siaptekno.barvolume

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //create variable that will contain the view
    private lateinit var widthEdt: EditText
    private lateinit var heightEdt: EditText
    private lateinit var lengthEdt: EditText
    private lateinit var calculateBtn: Button
    private lateinit var resultTv: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //initialize the variable that have been created
        lengthEdt = findViewById(R.id.lengthEdt)
        widthEdt = findViewById(R.id.widthEdt)
        heightEdt = findViewById(R.id.heightEdt)
        calculateBtn = findViewById(R.id.resultBtn)
        resultTv = findViewById(R.id.resultTv)
        calculateBtn.setOnClickListener (this)

        //calling onSaved on onCreate to restore the data
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            resultTv.text = result
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, resultTv.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.resultBtn) {
            val inputLength = lengthEdt.text.toString().trim()
            val inputWidth = widthEdt.text.toString().trim()
            val inputHeight = heightEdt.text.toString().trim()
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                lengthEdt.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                widthEdt.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                heightEdt.error = "Field ini tidak boleh kosong"
            }
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                resultTv.text = volume.toString()
            }
        }
    }
}
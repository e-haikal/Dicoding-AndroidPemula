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
import com.siaptekno.barvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
/*
//    Practice viewBinding
//    Delete the following code
    //create variable that will contain the view
    private lateinit var widthEdt: EditText
    private lateinit var heightEdt: EditText
    private lateinit var lengthEdt: EditText
    private lateinit var calculateBtn: Button
    private lateinit var resultTv: TextView
*/

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//    Practice viewBinding

        binding.calculateBtn.setOnClickListener(this)

        //calling onSaved on onCreate to restore the data
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.resultTv.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, binding.resultTv.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.calculateBtn.id) {
            val inputLength = binding.lengthEdt.text.toString().trim()
            val inputWidth = binding.widthEdt.text.toString().trim()
            val inputHeight = binding.heightEdt.text.toString().trim()
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                binding.lengthEdt.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                binding.widthEdt.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                binding.heightEdt.error = "Field ini tidak boleh kosong"
            }
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                binding.resultTv.text = volume.toString()
            }
        }
    }
}
# Volume Calculator App

This application is a simple Android app developed in Kotlin that calculates the volume of a rectangular prism. Users can input the length, width, and height, and the app will compute and display the volume.

## Features

- Input fields for length, width, and height
- Calculate button to compute the volume
- Input validation to ensure all fields are filled
- State management to retain data during configuration changes (e.g., screen rotation)

## Code Explanation

### Package Declaration

package com.siaptekno.barvolume

This line defines the package name of the application, which helps organize the code and prevent naming conflicts.

### Imports

The necessary Android components are imported to use in the app.

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

### MainActivity Class

class MainActivity : AppCompatActivity(), View.OnClickListener

The MainActivity class extends AppCompatActivity to provide compatibility with modern Android features and implements View.OnClickListener to handle button clicks.

### Member Variables

private lateinit var widthEdt: EditText
private lateinit var heightEdt: EditText
private lateinit var lengthEdt: EditText
private lateinit var calculateBtn: Button
private lateinit var resultTv: TextView

These variables represent the UI components: input fields for dimensions and the button to trigger the calculation.

### Companion Object

companion object {
private const val STATE_RESULT = "state_result"
}

The companion object holds constants that can be accessed without creating an instance of the class, such as the key for saving the volume result.

### onCreate Method

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
enableEdgeToEdge()
setContentView(R.layout.activity_main)

The onCreate method initializes the activity. It sets the content view and enables edge-to-edge layout.

#### Adjusting Layout for System UI

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
insets
}

This code adjusts the layout to avoid overlapping with system UI elements like the status bar.

#### Initializing UI Components

lengthEdt = findViewById(R.id.lengthEdt)
widthEdt = findViewById(R.id.widthEdt)
heightEdt = findViewById(R.id.heightEdt)
calculateBtn = findViewById(R.id.resultBtn)
resultTv = findViewById(R.id.resultTv)
calculateBtn.setOnClickListener(this)

Each UI component is linked to its corresponding layout element, and the button is set to trigger the onClick method when clicked.

### Restoring State

if (savedInstanceState != null) {
val result = savedInstanceState.getString(STATE_RESULT)
resultTv.text = result
}

This block restores the volume result if the activity is recreated (e.g., during configuration changes).

### onSaveInstanceState Method

override fun onSaveInstanceState(outState: Bundle) {
super.onSaveInstanceState(outState)
outState.putString(STATE_RESULT, resultTv.text.toString())
}

This method saves the current volume result before the activity is destroyed.

### onClick Method

override fun onClick(v: View?) {
if (v?.id == R.id.resultBtn) {

The onClick method handles the button click event. It checks if the clicked view is the calculate button.

#### Input Validation

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

This section retrieves and trims input values, checks for empty fields, and sets error messages as needed.

#### Calculating Volume

if (!isEmptyFields) {
val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
resultTv.text = volume.toString()
}

If all fields are filled, the app calculates the volume and displays it in the result text view.

## Conclusion

This Kotlin application demonstrates essential concepts in Android development, including UI interaction, input validation, state management, and lifecycle handling. By following this code, beginners can learn to create functional Android apps.

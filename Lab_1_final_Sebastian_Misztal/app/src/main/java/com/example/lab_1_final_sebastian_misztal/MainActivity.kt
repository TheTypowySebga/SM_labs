package com.example.lab_1_final_sebastian_misztal

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab_1_final_sebastian_misztal.ui.theme.Lab_1_final_Sebastian_MisztalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val editTextRow1 = EditText(this).apply {
            hint = "Type your weight in KG"
            inputType = InputType.TYPE_CLASS_NUMBER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        mainLayout.addView(editTextRow1)

        val editTextRow2 = EditText(this).apply {
            hint = "Type your height in CM"
            inputType = InputType.TYPE_CLASS_NUMBER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        mainLayout.addView(editTextRow2)

        val buttonCalculate = Button(this).apply {
            text = "Calculate my BMI"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        mainLayout.addView(buttonCalculate)

        val textViewResult = TextView(this).apply {
            text = ""
            textSize = 18f
            gravity = Gravity.START
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 16
            }
        }
        mainLayout.addView(textViewResult)

        setContentView(mainLayout)

        buttonCalculate.setOnClickListener {
            val input1 = editTextRow1.text.toString()
            val input2 = editTextRow2.text.toString()

            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(this, "Enter number in both fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (input1.toFloat() == 0.toFloat()){
                Toast.makeText(this, "Weight can't be equal 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (input2.toFloat() == 0.toFloat()){
                Toast.makeText(this, "Height can't be equal 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            try {
                val number1 = input1.toFloat()
                val number2 = input2.toFloat() / 100
                val result = number1 / (number2 * number2)
                var diagnose = ""
                if (result < 18.5)
                    diagnose = "Underweight \uD83E\uDDB4"
                else if (result < 25.0)
                    diagnose = "Healthy \uD83C\uDF4E"
                else if (result < 30.0)
                    diagnose = "Overweight \u2696\uFE0F"
                else
                    diagnose = "Obesity \uD83D\uDED1"
                textViewResult.text = "Diagnose: $diagnose\nBMI value:  $result"
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Wprowadź poprawne liczby!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
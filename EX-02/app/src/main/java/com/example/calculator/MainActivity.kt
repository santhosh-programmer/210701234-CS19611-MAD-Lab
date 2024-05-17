package com.example.calculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var workstr:TextView
    private lateinit var resultview:TextView
    private  var input=StringBuilder()
    private var operator:Char=' '
    private var outputn:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workstr = findViewById<TextView>(R.id.workingstv)
        resultview = findViewById<TextView>(R.id.resulttv)
        setButtonClickListeners()
    }

    private fun setButtonClickListeners()
    {
        val btns= arrayOf(
            R.id.button_0,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,
            R.id.button_8,R.id.button_9,R.id.button_add,R.id.button_multiply,R.id.button_divide,R.id.button_subtract,R.id.button_equal
        )
        for(btn in btns)
        {
            findViewById<Button>(btn).setOnClickListener{onButtonClick(it)}
        }
        findViewById<Button>(R.id.button_equal).setOnClickListener{equalaction()}
        findViewById<Button>(R.id.button_all_clear).setOnClickListener(clearAll())
        findViewById<Button>(R.id.button_clear).setOnClickListener(backspace())
    }

    private fun equalaction()
    {
        if(input.isNotEmpty()) {
//            println(input)
            val secondNum=input.toString().toDouble()
            calculateResult(secondNum)
        }
    }

    private fun clearAll(): View.OnClickListener {
        return View.OnClickListener {
            workstr.text = ""
            resultview.text = ""
            input.clear()
            outputn = 0.0
            operator = ' '
        }
    }
    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        if (buttonText in "0123456789.") {
            input.append(buttonText)
            workstr.text = input.toString()
        } else if (buttonText in "+-*/") {
            if (input.isNotEmpty()) {
                val firstNum = input.toString().toDouble()
                calculateResult(firstNum)
                input.clear()
                operator = buttonText[0]
            }
        }
    }
    private fun calculateResult(secondNum: Double) {
        try {
            outputn = when (operator) {
                '+' -> outputn + secondNum
                '-' -> outputn - secondNum
                '*' -> outputn * secondNum
                '/' -> outputn / secondNum
                else -> secondNum
            }
            resultview.text = "$outputn"
        } catch (e: ArithmeticException) {
            resultview.text = "Error"
        } catch (e: Exception) {
            resultview.text = "Error"
        }
    }




    private fun backspace(): View.OnClickListener {
        return View.OnClickListener {
            val length = input.length
            if (length > 0) {
                input.deleteCharAt(length - 1)
                workstr.text = input.toString()
            }
        }
    }
}
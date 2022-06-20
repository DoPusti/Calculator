package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvOutput : TextView? = null
    private var lastNumeric : Boolean = false
    private var dotSet : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOutput = findViewById(R.id.tvOutput)


    }
    fun onDigit(view : View) {
        Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        // Nur Button hat das text-Attribut, daher muss die View als Button deklariert werden
        val b : Button = view as Button
        tvOutput?.append(b.text)
        lastNumeric = true

        /* ODER
        tvOutput?.append((view as Button).text)


         */

    }
    fun onClear(view: View) {
        tvOutput?.text = " "
    }

    fun onEquals(view: View) {
        if(lastNumeric) {
            var tvValue = tvOutput?.text.toString()
            var prefix  = ""
            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                val splitValue =tvValue.split("-")
                val one = splitValue[0]
                val two = splitValue[1]


                tvOutput?.text = (one.toDouble() - two.toDouble()).toString()


            } catch (e: ArithmeticException) {
                tvOutput?.text = e.toString()
                e.printStackTrace()
            }
        }
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !dotSet) {
            lastNumeric = false
            tvOutput?.append(".")
            dotSet = true
        }
    }

    fun onOperator(view: View) {
        tvOutput?.text?.let {

            if(lastNumeric && !isOperatorAdded(it.toString())) {
                tvOutput!!.append((view as Button).text.toString())
                lastNumeric = false
                dotSet = false
            }
        }
    }
    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")) {
            false
        }else {
            value.contains("/") ||
            value.contains("*") ||
            value.contains("+")||
            value.contains("-")
        }
    }

}

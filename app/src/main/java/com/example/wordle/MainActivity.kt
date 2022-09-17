package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wordle.FourLetterWordList
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val button = findViewById<Button>(R.id.button)
        var answer = findViewById<TextView>(R.id.answer)
        var guess1 = findViewById<TextView>(R.id.guess1)
        var guess2 = findViewById<TextView>(R.id.guess2)
        var guess3 = findViewById<TextView>(R.id.guess3)
        var check1 = findViewById<TextView>(R.id.check1)
        var check2 = findViewById<TextView>(R.id.check2)
        var check3 = findViewById<TextView>(R.id.check3)

        var g1 = findViewById<TextView>(R.id.g1)
        var g2 = findViewById<TextView>(R.id.g2)
        var g3 = findViewById<TextView>(R.id.g3)
        var c1 = findViewById<TextView>(R.id.c1)
        var c2 = findViewById<TextView>(R.id.c2)
        var c3 = findViewById<TextView>(R.id.c3)

        var input = findViewById<EditText>(R.id.et_simple)
        var count = 0
        var word = FourLetterWordList.getRandomFourLetterWord()

        if (count == 0) {
            answer.text = "????"
        }
        button.setOnClickListener {
            answer.text = ""
            count++
            if (count==1) {
                g1.visibility = View.VISIBLE
                c1.visibility = View.VISIBLE
                guess1.text = input.text
                check1.text = checkGuess(guess1.text.toString(), word)
                input.text.clear() //To clear the edit text
                input.hideKeyboard()

            }
            if (count==2) {
                g2.visibility = View.VISIBLE
                c2.visibility = View.VISIBLE
                guess2.text = input.text
                check2.text = checkGuess(guess2.text.toString(), word)
                input.text.clear()
                input.hideKeyboard()
            }
            if (count>=3) {
                g3.visibility = View.VISIBLE
                c3.visibility = View.VISIBLE
                guess3.text = input.text
                check3.text = checkGuess(guess3.text.toString(), word)
                input.text.clear()
                input.hideKeyboard()
                val loser = Toast.makeText(applicationContext, "Oops, you've exceeded your number of guesses!", Toast.LENGTH_LONG)
                loser.setGravity(Gravity.CENTER_HORIZONTAL,0,0)
                loser.show()
                answer.text = word
            }

        }

    }

    private fun showToast(str:String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun checkGuess(guesses: String, wordToGuess: String): String {
        var guess = guesses.uppercase() //Makes all guesses uppercase
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

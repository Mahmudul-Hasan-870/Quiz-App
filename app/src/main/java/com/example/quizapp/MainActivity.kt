package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("What is the capital of France?", listOf("Berlin", "Madrid", "Paris", "Lisbon"), 2),
        Question("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
        Question("What is the largest planet?", listOf("Earth", "Mars", "Jupiter", "Saturn"), 2)
    )

    private var currentQuestionIndex = 0

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        optionsRadioGroup = findViewById(R.id.options_radio_group)
        submitButton = findViewById(R.id.submit_button)

        loadQuestion()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        questionTextView.text = question.text
        optionsRadioGroup.removeAllViews()
        for ((index, option) in question.options.withIndex()) {
            val radioButton = RadioButton(this)
            radioButton.id = index
            radioButton.text = option
            optionsRadioGroup.addView(radioButton)
        }
    }

    private fun checkAnswer() {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            return
        }

        val correctAnswerIndex = questions[currentQuestionIndex].answerIndex
        if (selectedOptionId == correctAnswerIndex) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        loadQuestion()
    }
}

package com.example.kotlinmvvmtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmtutorial.databinding.ActivityMainBinding
import com.example.kotlinmvvmtutorial.viewmodel.ActionType
import com.example.kotlinmvvmtutorial.viewmodel.NumberViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var numberViewModel: NumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        binding.buttonPlus.setOnClickListener(this)
        binding.buttonMinus.setOnClickListener(this)

        numberViewModel = ViewModelProvider(this).get(NumberViewModel::class.java)
        numberViewModel.currentValue.observe(this, Observer {
            binding.textViewNumber.text = it.toString()
        })
    }

    override fun onClick(v: View?) {

        val userInput = binding.editTextInput.text.toString().toInt()

        when(v) {
            binding.buttonPlus -> {
                numberViewModel.updateValue(actionType = ActionType.PLUS, input = userInput)
            }
            binding.buttonMinus -> {
                numberViewModel.updateValue(actionType = ActionType.MINUS, input = userInput)
            }
        }
    }
}
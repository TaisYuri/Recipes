package com.example.recipes.presentation.components

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.recipes.R
import com.example.recipes.databinding.ResFollowersIndicatorBinding

class FollowersIndicator(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: ResFollowersIndicatorBinding =
        ResFollowersIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
      /*  context.obtainStyledAttributes(attrs, R.styleable.FollowersIndicator).apply {
            binding.tvCounter.text = getString(R.styleable.FollowersIndicator_counter)
            binding.tvIndicator.text = getString(R.styleable.FollowersIndicator_indicator)
        }*/

        //PASSANDO AS FUNÇÕES CRIADAS ABAIXO
        context.obtainStyledAttributes(attrs, R.styleable.FollowersIndicator).apply {
            getString(R.styleable.FollowersIndicator_counter)?.let { setCounter(it) }
            getString(R.styleable.FollowersIndicator_indicator)?.let { setIndicator(it) }

            setBold(getBoolean(R.styleable.FollowersIndicator_bold, true))
        }
    }
    fun setCounter(counter: String){
        binding.tvCounter.text = counter
    }

    fun setIndicator(counter: String){
        binding.tvIndicator.text = counter
    }

    fun setBold(isBold: Boolean){
        if (isBold) {
            binding.tvCounter.typeface = Typeface.DEFAULT_BOLD
        } else {
            binding.tvCounter.typeface = Typeface.DEFAULT
        }
    }

}
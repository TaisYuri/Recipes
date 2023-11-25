package com.example.recipes.presentation.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.recipes.R
import com.example.recipes.databinding.ResProfileImageBinding
import java.security.AccessController.getContext


class ProfileImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private val binding: ResProfileImageBinding =
        ResProfileImageBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ProfileImage).apply {
            setImageProfile(getResourceId(R.styleable.ProfileImage_src, 0))
            setVisualized(getInt(R.styleable.ProfileImage_visualized, 0))
            //setVisualized(getBoolean(R.styleable.ProfileImage_visualized, false))
        }
    }

    fun setImageProfile(resourceId: Int) {
        binding.imageProfile.setImageResource(resourceId)
    }

    fun setVisualized(isVisualized: Int) {
        if (isVisualized == 1) {
            binding.cvProfileImage.setCardBackgroundColor(Color.parseColor("#c6c6c6"))
        } else {
            binding.cvProfileImage.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.orange
                )
            )
        }
    }

}

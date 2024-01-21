package com.cjimenezro.superheroes.app.presentation.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.cjimenezro.superheroes.app.extensions.hide
import com.cjimenezro.superheroes.app.extensions.show
import com.cjimenezro.superheroes.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        visibility = View.GONE
    }

    fun render(errorUiModel: ErrorUiModel) {
        visibility = View.VISIBLE
        binding.apply {
            labelTitleError.text = context.getString(errorUiModel.getTitle())
            labelDescriptionError.text = context.getString(errorUiModel.getDescription())
            imageError.setImageResource(errorUiModel.getImage())
            errorUiModel.onClickRetry()?.apply {
                actionRetry.show()
                actionRetry.setOnClickListener {
                    this.invoke()
                }
            } ?: {
                actionRetry.hide()
            }
        }
    }
}
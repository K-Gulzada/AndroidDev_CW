package com.example.androiddevhw_2.lesson_13.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.WidgetFeedbackBinding

// JvmOverloads - обязательна, почитай почему (xml не отрисуется без него)


class FeedbackWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // 1 => 1 and 2 are the same

  /*  private var binding: WidgetFeedbackBinding =
        WidgetFeedbackBinding.inflate(LayoutInflater.from(context), this, true)*/

    // 2

    private var binding: WidgetFeedbackBinding =
        WidgetFeedbackBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        context.withStyledAttributes(attrs, R.styleable.FeedbackWidget){
            binding.apply {
                feedbackTitle.setHint(getString(R.styleable.FeedbackWidget_titleHint))
                feedbackBtn.text = (getString(R.styleable.FeedbackWidget_btnText))
                feedbackBtn.setTextColor(getColor(R.styleable.FeedbackWidget_btnColor, Color.BLUE))
                feedbackBtn.textSize = (getDimension(R.styleable.FeedbackWidget_btnTextSize, 16f))
            }
        }
    }
    @SuppressWarnings("Callback Memory Leak")
    fun onEndIconClick(callback:(String)-> Unit){
        binding.feedbackTitleEndIcon.setOnClickListener{
            callback.invoke(binding.feedbackTitle.text.toString())
        }
    }

}
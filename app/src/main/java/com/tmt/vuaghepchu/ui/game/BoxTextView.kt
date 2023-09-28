package com.tmt.vuaghepchu.ui.game

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.tmt.vuaghepchu.R

class BoxTextView : ConstraintLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        initViews(context, attrs)
    }

    private lateinit var tvValue: TextView
    private var hasDash = false

    private fun initViews(context: Context?, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.frame_box_view, this)
        val typeArray = context?.obtainStyledAttributes(attrs, R.styleable.FrameBox)
        val imvContent: ImageView = findViewById(R.id.box_iv_content)
        tvValue = findViewById(R.id.box_tv_content)

        if (typeArray != null) {
            if (typeArray.getResourceId(R.styleable.FrameBox_bg_box, -1) != -1) {
                imvContent.setImageDrawable(context.let {
                    ContextCompat.getDrawable(
                        it, typeArray.getResourceId(
                            R.styleable.FrameBox_bg_box,
                            R.drawable.img_box_purple
                        )
                    )
                })
            }
            if (typeArray.getString(R.styleable.FrameBox_box_value) != null) {
                tvValue.text =
                    typeArray.getString(R.styleable.FrameBox_box_value)
            }
            tvValue.setTextColor(
                (typeArray.getColor(
                    R.styleable.FrameBox_text_color,
                    ContextCompat.getColor(context, R.color.white)
                ))
            )
            hasDash = typeArray.getBoolean(R.styleable.FrameBox_has_dash, false) ?: false
        }
    }

    fun setText(value: String) {
        tvValue.text = value
    }

    fun getTextBox(): String {
        return ((tvValue.text) ?: "") as String
    }
}
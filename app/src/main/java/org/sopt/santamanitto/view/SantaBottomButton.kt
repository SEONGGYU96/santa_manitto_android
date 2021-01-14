package org.sopt.santamanitto.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import org.sopt.santamanitto.*

class SantaBottomButton : AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setBackgroundResource(R.drawable.round_ractangle_background)
        if (isEnabled) {
            setBackgroundTint(R.color.red)
            setTextColorById(R.color.white)
        }
        setTextSize(R.dimen.size_santabottombutton_text)
        setPaddingVerticalById(R.dimen.padding_santabottombutton_vertical)
        setRippleEffect(true)
        setBold(true)
    }

    override fun setEnabled(enabled: Boolean) {
        setBackgroundResource(R.drawable.round_ractangle_background)
        setBackgroundTint(R.color.gray_1)
        setTextColorById(R.color.gray_2)
        super.setEnabled(enabled)
    }
}
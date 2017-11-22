package mi.mobile.midevtest.view


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import mi.mobile.midevtest.R


/**
 * Created by ikhsan on 22/11/17.
 */
class MITextView : TextView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        applyCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        applyCustomFont(context, attrs)
    }

    private fun applyCustomFont(context: Context, attrs: AttributeSet) {
        val attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MIFontTextView)

        val textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL)

        val customFont = selectTypeface(context, textStyle)
        typeface = customFont

        attributeArray.recycle()
    }

    private fun selectTypeface(context: Context, textStyle: Int): Typeface? {
        when (textStyle) {
            Typeface.BOLD // bold
            -> return FontCache.getTypeface("Delicious-Bold.otf", context)

            Typeface.NORMAL // regular
            -> return FontCache.getTypeface("Delicious-Roman.otf", context)
            else -> return FontCache.getTypeface("Delicious-Roman.otf", context)
        }
    }

    companion object {

        val ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android"
    }
}
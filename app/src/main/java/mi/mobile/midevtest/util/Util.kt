package mi.mobile.midevtest.util

import android.app.Activity
import android.graphics.Typeface
import android.support.v7.widget.Toolbar
import android.widget.TextView

/**
 * Created by ikhsan on 22/11/17.
 */
class Util
{
    companion object {
        fun changeToolbarFont(toolbar: Toolbar, context: Activity) {
            for (i in 0 until toolbar.childCount) {
                val view = toolbar.getChildAt(i)
                if (view is TextView) {
                    if (view.text == toolbar.getTitle()) {
                        view.typeface = Typeface.createFromAsset(context.assets,
                                "Delicious-Bold.otf")
                        break
                    }
                }
            }
        }
    }
}
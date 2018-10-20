package com.example.skeleton.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.skeleton.R
import com.example.skeleton.helper.LP
import com.example.skeleton.helper.ResourceHelper.dimen
import com.example.skeleton.helper.ResourceHelper.dp
import com.example.skeleton.helper.ResourceHelper.font
import com.example.skeleton.helper.ResourceHelper.color

@Suppress("unused", "UNUSED_PARAMETER")
class ActionBar : RelativeLayout {
    private val mLeft: LinearLayout
    private val mRight: LinearLayout
    private val mCenter: FrameLayout
    private var mTitle: TextView? = null

    //region Lifecycle
    //---------------------------------------------------------------
    constructor(context: Context) : super(context) {
        mLeft = LinearLayout(context)
        mCenter = FrameLayout(context)
        mRight = LinearLayout(context)
        initView(context, null, 0)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mLeft = LinearLayout(context)
        mCenter = FrameLayout(context)
        mRight = LinearLayout(context)
        initView(context, attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        mLeft = LinearLayout(context)
        mCenter = FrameLayout(context)
        mRight = LinearLayout(context)
        initView(context, attrs, defStyle)
    }
    private fun initView(context: Context, attrs: AttributeSet?, defStyle: Int) {
        mLeft.orientation = LinearLayout.HORIZONTAL
        mLeft.gravity = Gravity.CENTER_VERTICAL
        mRight.orientation = LinearLayout.HORIZONTAL
        mRight.gravity = Gravity.CENTER_VERTICAL

        setPadding(dp(8), dp(8), dp(8), dp(8))
        gravity = Gravity.CENTER_VERTICAL
        addView(mLeft, LP.relative(LP.WRAP_CONTENT, LP.WRAP_CONTENT).build())
        addView(mCenter, LP.relative(LP.MATCH_PARENT, LP.WRAP_CONTENT).build())
        addView(mRight, LP.relative(LP.WRAP_CONTENT, LP.WRAP_CONTENT).alignParentEnd().build())
    }
    //---------------------------------------------------------------
    //endregion

    //region Title
    //---------------------------------------------------------------
    private fun raiiTitle() {
        if (mTitle != null) return
        mTitle = TextView(context).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            typeface = font(R.font.barlow_condensed_medium)
            textSize = 22f
            setTextColor(color(R.color.primary))
        }
        mCenter.addView(mTitle, LP.frame(LP.WRAP_CONTENT, LP.WRAP_CONTENT, Gravity.CENTER).build())
    }
    fun setTitle(@StringRes title: Int) {
        raiiTitle()
        mTitle?.setText(title)
    }
    fun setTitle(title: String) {
        raiiTitle()
        mTitle?.text = title
    }
    fun setTitleView(v: View) {
        mCenter.addView(mTitle, LP.frame(LP.WRAP_CONTENT, LP.WRAP_CONTENT, Gravity.CENTER)
                .build())
    }
    //---------------------------------------------------------------
    //endregion

    //region Button
    //---------------------------------------------------------------
    fun addLeftButton(@DrawableRes icon: Int, listener: View.OnClickListener) {
        val button = Button(context)
        button.setIcon(icon, dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
        button.setOnClickListener(listener)
        mLeft.addView(button, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
                                .setMargins(0, 0, dp(16), 0)
                                .build())
    }
    fun addLeftPadding() {
        val view = View(context)
        mLeft.addView(view, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
                .setMargins(0, 0, dp(16), 0)
                .build())
    }
    fun addRightButton(@DrawableRes icon: Int, listener: View.OnClickListener) {
        val button = Button(context)
        button.setIcon(icon, dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
        button.setOnClickListener(listener)
        mRight.addView(button, 0, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
                .setMargins(0, 0, dp(16), 0)
                .build())
    }
    fun addRightPadding() {
        val view = View(context)
        mRight.addView(view, 0, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon))
                .setMargins(0, 0, dp(16), 0)
                .build())
    }
    fun addLeftView(view: View) {
        mLeft.addView(view, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon)).build())
    }
    fun addRightView(view: View) {
        mRight.addView(view, LP.linear(dimen(R.dimen.actionbar_icon), dimen(R.dimen.actionbar_icon)).build())
    }
    //---------------------------------------------------------------
    //endregion
}

package com.nelsito.fifteenpuzzle

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class SquareRelativeLayout : RelativeLayout {
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}
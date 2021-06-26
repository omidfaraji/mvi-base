package com.faraji.mvibase.example.utils.extension

import android.content.res.Resources
import android.util.TypedValue

val Number.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    )
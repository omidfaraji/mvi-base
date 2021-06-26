package com.faraji.mvibase.example.data.mapper

import com.faraji.mvibase.example.data.model.SizeData
import com.faraji.mvibase.example.domain.model.request.Size

fun Size.toData(): SizeData {
    return when (this) {
        Size.FULL -> SizeData.FULL
        Size.MED -> SizeData.MED
        Size.SMALL -> SizeData.SMALL
        Size.THUMB -> SizeData.THUMB
    }
}
package com.faraji.mvibase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseMviActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {

    }
}


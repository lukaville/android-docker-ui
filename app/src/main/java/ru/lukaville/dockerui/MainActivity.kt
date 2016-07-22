package ru.lukaville.dockerui

import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_one_fragment
    }
}

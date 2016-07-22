package ru.lukaville.dockerui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import ru.lukaville.dockerui.util.bindView

abstract class BaseActivity : AppCompatActivity(), KodeinInjected {

    val toolbar: Toolbar by bindView(R.id.toolbar)

    override val injector = KodeinInjector()

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        injector.inject(appKodein())
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }
}
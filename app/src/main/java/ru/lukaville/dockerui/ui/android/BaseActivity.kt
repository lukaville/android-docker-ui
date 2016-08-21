package ru.lukaville.dockerui.ui.android

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.util.bindView

abstract class BaseActivity : AppCompatActivity(), KodeinInjected {
    companion object {
        const val BACK_ARROW_INDICATOR = -1
    }

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

        val actionBar = supportActionBar

        if (getHomeAsUpIndicator() != 0) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            if (getHomeAsUpIndicator() != BACK_ARROW_INDICATOR) {
                actionBar?.setHomeAsUpIndicator(getHomeAsUpIndicator())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @DrawableRes
    protected open fun getHomeAsUpIndicator(): Int {
        return 0
    }
}
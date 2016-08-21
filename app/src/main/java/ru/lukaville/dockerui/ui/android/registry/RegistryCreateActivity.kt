package ru.lukaville.dockerui.ui.android.registry

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Credentials
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.android.BaseActivity
import ru.lukaville.dockerui.ui.view.RegistryCreateView
import ru.lukaville.dockerui.util.bindView
import rx.Observable
import rx.subjects.PublishSubject

class RegistryCreateActivity : BaseActivity(), RegistryCreateView {
    val createRegistry: PublishSubject<Registry> = PublishSubject.create()

    val nameEditText: EditText by bindView(R.id.name)
    val urlEditText: EditText by bindView(R.id.url)
    val loginEditText: EditText by bindView(R.id.login)
    val passwordEditText: EditText by bindView(R.id.password)

    override fun getLayout(): Int {
        return R.layout.activity_registry_create
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun onCreateRegistry() {
        val credentials = when (loginEditText.length()) {
            0 -> null
            else -> Credentials(
                    loginEditText.text.toString(),
                    passwordEditText.text.toString()
            )
        }

        val registry = Registry(
                urlEditText.text.toString(),
                nameEditText.text.toString(),
                credentials
        )

        createRegistry.onNext(registry)
    }

    override fun createRegistry(): Observable<Registry> {
        return createRegistry
    }

    override fun getHomeAsUpIndicator(): Int {
        return R.drawable.ic_close_white_24dp
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_registry_create, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_create -> {
                onCreateRegistry()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
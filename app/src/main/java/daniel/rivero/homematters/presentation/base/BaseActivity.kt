package daniel.rivero.homematters.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel<VS, *>, VS: ViewState> : AppCompatActivity(), Renderable<VS> {

    @Inject
    lateinit var viewModel: VM

    val activityComponent by lazy {
        (application as AndroidApplication)
                .applicationComponent
                .viewComponent(ViewModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(activityComponent)

        val factory = ViewModelFactory.createFor(viewModel)
        ViewModelProviders.of(this, factory).get(viewModel::class.java)

        viewModel.viewState.observe(this, Observer { render(it) })
    }

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    protected fun initFragmentContainer(containerId: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.commit()
    }

    fun showMessage(message: String) {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

}
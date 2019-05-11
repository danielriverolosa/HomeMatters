package daniel.rivero.homematters.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import javax.inject.Inject


abstract class BaseFragment<VM: BaseViewModel<VS, *>, VS: ViewState> : Fragment(), Renderable<VS> {

    @Inject
    lateinit var viewModel: VM

    private val fragmentComponent by lazy {
        (activity?.application as AndroidApplication)
                .applicationComponent
                .viewComponent(ViewModule(activity as BaseActivity<*, *>))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(fragmentComponent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.createFor(viewModel)
        ViewModelProviders.of(this, factory).get(viewModel::class.java)

        viewModel.viewState.observe(this, Observer { render(it) })
    }

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    fun showMessage(message: String) {
        (activity as BaseActivity<*, *>).showMessage(message)
    }

}
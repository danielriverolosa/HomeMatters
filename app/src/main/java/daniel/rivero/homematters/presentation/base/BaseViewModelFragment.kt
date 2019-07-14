package daniel.rivero.homematters.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import javax.inject.Inject


abstract class BaseViewModelFragment<VM: BaseViewModel<VS, *>, VS: ViewState> : BaseFragment(), Renderable<VS> {

    @Inject
    lateinit var viewModel: VM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.createFor(viewModel)
        ViewModelProviders.of(this, factory).get(viewModel::class.java)

        viewModel.viewState.observe(this, Observer { render(it) })
    }

}
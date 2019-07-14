package daniel.rivero.homematters.presentation.main.home.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.main.home.event.HomeUnselectedEvent
import daniel.rivero.homematters.presentation.main.home.viewmodel.HomeUnselectedViewModel
import daniel.rivero.homematters.presentation.main.home.viewstate.HomeUnselectedViewState
import kotlinx.android.synthetic.main.fragment_home_unselected.*

@ContentView(R.layout.fragment_home_unselected)
class HomeUnselectedFragment: BaseViewModelFragment<HomeUnselectedViewModel, HomeUnselectedViewState>() {

    companion object {
        fun getInstance() = HomeUnselectedFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newHouseButton.setOnClickListener { viewModel.onEvent(HomeUnselectedEvent.NewHome) }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: HomeUnselectedViewState) {
        // do nothing
    }

}
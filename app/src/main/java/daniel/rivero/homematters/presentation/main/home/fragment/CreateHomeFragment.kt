package daniel.rivero.homematters.presentation.main.home.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.invisible
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.main.home.event.CreateHomeEvent
import daniel.rivero.homematters.presentation.main.home.viewmodel.CreateHomeViewModel
import daniel.rivero.homematters.presentation.main.home.viewstate.CreateHomeViewState
import kotlinx.android.synthetic.main.fragment_create_home.*

@ContentView(R.layout.fragment_create_home)
class CreateHomeFragment : BaseViewModelFragment<CreateHomeViewModel, CreateHomeViewState>() {

    companion object {
        fun getInstance() = CreateHomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener { viewModel.onEvent(CreateHomeEvent.Continue(nameInput.text.toString())) }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: CreateHomeViewState) {
        when (viewState) {
            CreateHomeViewState.Loading -> showLoading()
            CreateHomeViewState.InputError -> showError()
            CreateHomeViewState.GenericError -> showGenericError()
        }
    }

    private fun showLoading() {
        continueButton.invisible()
        loadingView.show()
    }

    private fun showError() {
        continueButton.show()
        loadingView.invisible()
        nameInputLayout.error = context?.getString(R.string.general_required_field)
    }

    private fun showGenericError() {
        continueButton.show()
        continueButton.isClickable = false
        continueButton.alpha = 0.4f
        loadingView.invisible()
        errorText.show()
    }

}
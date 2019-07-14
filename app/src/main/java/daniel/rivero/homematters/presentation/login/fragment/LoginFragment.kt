package daniel.rivero.homematters.presentation.login.fragment

import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.invisible
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.login.viewmodel.LoginViewModel
import daniel.rivero.homematters.presentation.login.viewstate.LoginViewState
import kotlinx.android.synthetic.main.fragment_login.*

@ContentView(R.layout.fragment_login)
class LoginFragment: BaseViewModelFragment<LoginViewModel, LoginViewState>() {

    companion object {
        fun getInstance() = LoginFragment()
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: LoginViewState) {
        when(viewState) {
            LoginViewState.Loading -> showLoadingView()
        }
    }

    private fun showLoadingView() {
        continueButton.invisible()
        loadingView.show()
    }

}
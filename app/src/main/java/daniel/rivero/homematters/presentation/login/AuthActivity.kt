package daniel.rivero.homematters.presentation.login

import android.os.Bundle
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseActivity
import daniel.rivero.homematters.presentation.login.event.AuthEvent
import daniel.rivero.homematters.presentation.login.viewmodel.AuthViewModel
import daniel.rivero.homematters.presentation.login.viewstate.AuthViewState

class AuthActivity: BaseActivity<AuthViewModel, AuthViewState>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.onEvent(AuthEvent.Login)
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: AuthViewState) {
        // nothing to render
    }
}
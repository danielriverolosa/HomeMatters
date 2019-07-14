package daniel.rivero.homematters.presentation.login.viewmodel

import daniel.rivero.homematters.domain.interactor.login.LoginDto
import daniel.rivero.homematters.domain.interactor.login.LoginUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.login.event.LoginEvent
import daniel.rivero.homematters.presentation.login.viewstate.LoginViewState
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: AndroidApplication,
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginViewState, LoginEvent>(application) {

    override fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.SignUp -> navigator.showSignUp()
            LoginEvent.RecoveryPassword -> navigator.showRecoveryPassword()
            is LoginEvent.Continue ->  validateAndLogin(event.email, event.password)
        }
    }

    private fun validateAndLogin(email: String, password: String) {
        updateViewState(LoginViewState.Loading)

        disposables.add(
            loginUseCase(LoginDto(email, password))
                .subscribe(navigator::goToMain) {
                    updateViewState(LoginViewState.InvalidCredentials)
                }
        )
    }

}
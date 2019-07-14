package daniel.rivero.homematters.presentation.login.viewmodel

import daniel.rivero.homematters.domain.interactor.signup.SignUpDto
import daniel.rivero.homematters.domain.interactor.signup.SignUpUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.login.event.SignUpEvent
import daniel.rivero.homematters.presentation.login.viewstate.SignUpViewState
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    app: AndroidApplication,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpViewState, SignUpEvent>(app) {
    override fun onEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.Login -> navigator.showLogin()
            is SignUpEvent.Continue -> signUpAndValidate(event.name, event.email, event.password, event.confirmPassword)
        }
    }

    private fun signUpAndValidate(name: String, email: String, password: String, confirmPassword: String) {
        updateViewState(SignUpViewState.Loading)
        disposables.add(
            signUpUseCase(SignUpDto(name, email, password, confirmPassword))
                .subscribe(navigator::goToMain) {
                    updateViewState(SignUpViewState.CredentialsError)
                }
        )
    }

}
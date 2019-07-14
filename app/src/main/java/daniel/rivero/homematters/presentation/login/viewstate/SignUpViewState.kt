package daniel.rivero.homematters.presentation.login.viewstate

import daniel.rivero.homematters.presentation.base.ViewState

sealed class SignUpViewState: ViewState {
    object Loading: SignUpViewState()
    object CredentialsError: SignUpViewState()
}
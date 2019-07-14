package daniel.rivero.homematters.presentation.main.home.viewstate

import daniel.rivero.homematters.presentation.base.ViewState

sealed class CreateHomeViewState : ViewState {

    object Loading: CreateHomeViewState()
    object InputError: CreateHomeViewState()
    object GenericError: CreateHomeViewState()

}
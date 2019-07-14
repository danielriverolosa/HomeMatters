package daniel.rivero.homematters.presentation.main.home.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.interactor.home.create.RegisterHomeDto
import daniel.rivero.homematters.domain.interactor.home.create.RegisterHomeUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.home.event.CreateHomeEvent
import daniel.rivero.homematters.presentation.main.home.viewstate.CreateHomeViewState
import javax.inject.Inject

class CreateHomeViewModel @Inject constructor(
    app: AndroidApplication,
    private val registerHomeUseCase: RegisterHomeUseCase
) : BaseViewModel<CreateHomeViewState, CreateHomeEvent>(app) {

    override fun onEvent(event: CreateHomeEvent) {
        when (event) {
            is CreateHomeEvent.Continue -> onClickContinue(event)
        }
    }

    private fun onClickContinue(event: CreateHomeEvent.Continue) {
        event.name?.let { registerHome(it) } ?: updateViewState(CreateHomeViewState.InputError)
    }

    @SuppressLint("CheckResult")
    private fun registerHome(name: String) {
        registerHomeUseCase(RegisterHomeDto(name)).subscribe(::navigateToHouseMain) {
            updateViewState(CreateHomeViewState.GenericError)
        }
    }

    private fun navigateToHouseMain(home: Home) {
        navigator.showHomeSettings(home)
    }

}
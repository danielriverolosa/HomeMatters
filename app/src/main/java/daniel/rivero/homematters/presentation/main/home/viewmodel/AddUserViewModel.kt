package daniel.rivero.homematters.presentation.main.home.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.exception.UserAlreadyExistException
import daniel.rivero.homematters.domain.exception.UserNotFoundException
import daniel.rivero.homematters.domain.interactor.user.add.AddUserDto
import daniel.rivero.homematters.domain.interactor.user.add.AddUserUseCase
import daniel.rivero.homematters.domain.interactor.user.find.FindUserDto
import daniel.rivero.homematters.domain.interactor.user.find.FindUserUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.home.event.AddUserEvent
import daniel.rivero.homematters.presentation.main.home.viewstate.AddUserViewState
import javax.inject.Inject

class AddUserViewModel @Inject constructor(
    app: AndroidApplication,
    private val findUserUseCase: FindUserUseCase,
    private val addUserUseCase: AddUserUseCase
): BaseViewModel<AddUserViewState, AddUserEvent>(app) {

    private var userFinded: User? = null

    override fun onEvent(event: AddUserEvent) {
        when(event) {
            is AddUserEvent.FindUser -> findUser(event.email)
            is AddUserEvent.AddUser -> addUser(event.email, event.home)
        }
    }

    @SuppressLint("CheckResult")
    private fun findUser(email: String) {
        updateViewState(AddUserViewState.SearchLoading)
        findUserUseCase(FindUserDto(email)).subscribe(::onSuccessFindUser, ::handleError)
    }

    private fun onSuccessFindUser(user: User?) {
        user?.let {
            userFinded = it
            updateViewState(AddUserViewState.LoadUserData(user))
        } ?: updateViewState(AddUserViewState.UserNotFound)
    }

    @SuppressLint("CheckResult")
    private fun addUser(email: String, home: Home) {
        updateViewState(AddUserViewState.AddUserLoading)
        addUserUseCase.invoke(AddUserDto(email, home, userFinded?.id)).subscribe(::onComplete, ::handleError)
    }

    private fun onComplete() {
        updateViewState(AddUserViewState.UserAdded)
    }

    private fun handleError(t: Throwable) {
        val viewState = when(t) {
            is UserNotFoundException -> AddUserViewState.UserNotFound
            is UserAlreadyExistException -> AddUserViewState.UserAlreadyExists
            else -> AddUserViewState.GenericError
        }
        updateViewState(viewState)
    }

}
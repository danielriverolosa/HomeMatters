package daniel.rivero.homematters.domain.repository

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.login.LoginDto
import daniel.rivero.homematters.domain.interactor.signup.SignUpDto
import io.reactivex.Single

interface SecurityRepository {

    fun login(loginDto: LoginDto): Single<User>

    fun signUp(signUpDto: SignUpDto): Single<User>

}
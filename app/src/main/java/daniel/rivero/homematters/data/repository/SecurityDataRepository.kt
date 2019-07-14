package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.login.LoginApi
import daniel.rivero.homematters.data.datasource.api.login.model.LoginRequest
import daniel.rivero.homematters.data.datasource.api.signup.SignUpApi
import daniel.rivero.homematters.data.datasource.api.signup.model.SignUpRequest
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.login.LoginDto
import daniel.rivero.homematters.domain.interactor.signup.SignUpDto
import daniel.rivero.homematters.domain.repository.SecurityRepository
import io.reactivex.Single
import javax.inject.Inject

class SecurityDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
): SecurityRepository {
    override fun login(loginDto: LoginDto): Single<User> {
        val api = clientGenerator.generateApi(LoginApi::class)

        val response = api.login(LoginRequest(loginDto.email, loginDto.password))

        return apiResponseHandler.processResponse(response).map { User(it.id, it.name, it.email) }
    }

    override fun signUp(signUpDto: SignUpDto): Single<User> {
        val api = clientGenerator.generateApi(SignUpApi::class)

        val response = api.signUp(SignUpRequest(signUpDto.name, signUpDto.email, signUpDto.password, signUpDto.confirmPassword))

        return apiResponseHandler.processResponse(response).map { User(it.id, it.name, it.email) }
    }

}
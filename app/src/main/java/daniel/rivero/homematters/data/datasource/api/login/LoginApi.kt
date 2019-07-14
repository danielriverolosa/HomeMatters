package daniel.rivero.homematters.data.datasource.api.login

import daniel.rivero.homematters.data.datasource.api.login.model.LoginRequest
import daniel.rivero.homematters.data.datasource.api.login.model.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Single<Result<LoginResponse>>

}
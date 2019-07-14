package daniel.rivero.homematters.data.datasource.api.signup

import daniel.rivero.homematters.data.datasource.api.signup.model.SignUpRequest
import daniel.rivero.homematters.data.datasource.api.signup.model.SignUpResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("/signup")
    fun signUp(@Body resquest: SignUpRequest): Single<Result<SignUpResponse>>

}
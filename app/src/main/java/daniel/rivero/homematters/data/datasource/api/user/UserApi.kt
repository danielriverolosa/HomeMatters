package daniel.rivero.homematters.data.datasource.api.user

import daniel.rivero.homematters.data.datasource.api.user.model.UpdateHomeRequest
import daniel.rivero.homematters.data.datasource.api.user.model.UserRequest
import daniel.rivero.homematters.data.datasource.api.user.model.UserResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface UserApi {

    @GET("/usersByHouseId/{houseId}")
    fun getUsersByHouseId(@Path("houseId") houseId: String): Single<Result<List<UserResponse>>>

    @GET("/usersByEmail/{email}")
    fun getUsersEmail(@Path("email") email: String): Single<Result<List<UserResponse>>>

    @POST("/users")
    fun updateUser(@Body request: UserRequest): Completable

    @PUT("/users")
    fun updateHome(@Body request: UpdateHomeRequest): Completable

}
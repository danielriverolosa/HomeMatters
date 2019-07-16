package daniel.rivero.homematters.data.datasource.api.home

import daniel.rivero.homematters.data.datasource.api.home.model.HomeResponse
import daniel.rivero.homematters.data.datasource.api.user.model.HomeRequest
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApi {

    @GET("/houses/{userId}")
    fun getHomeByUserId(@Path("userId") userId: String): Single<Result<List<HomeResponse>>>

    @POST("/houses")
    fun editHouse(@Body request: HomeRequest): Single<Result<HomeResponse>>

}
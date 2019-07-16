package daniel.rivero.homematters.data.datasource.api.task

import daniel.rivero.homematters.data.datasource.api.task.model.AssignTaskRequest
import daniel.rivero.homematters.data.datasource.api.task.model.AssignedTaskResponse
import daniel.rivero.homematters.data.datasource.api.task.model.CustomTaskRequest
import daniel.rivero.homematters.data.datasource.api.task.model.TaskResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface TaskApi {

    @GET("/tasks")
    fun getTaskList(): Single<Result<List<TaskResponse>>>

    @GET("/custom/tasks/{houseId}")
    fun getCustomTasks(@Path("houseId") houseId: String): Single<Result<List<TaskResponse>>>

    @POST("/custom/tasks")
    fun createCustomTask(@Body request: CustomTaskRequest): Completable

    @POST("/assigned/tasks")
    fun assignTask(@Body request: AssignTaskRequest): Completable

    @GET("/assigned/tasks")
    fun getAssignedTaskList(
        @Query("houseId") userId: String,
        @Query("toDate") toDate: Long,
        @Query("fromDate") fromDate: Long
    ): Single<Result<List<AssignedTaskResponse>>>

}
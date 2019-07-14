package daniel.rivero.homematters.data.datasource.api.task

import daniel.rivero.homematters.data.datasource.api.task.model.AssignedTaskResponse
import daniel.rivero.homematters.data.datasource.api.task.model.TaskResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskApi {

    @GET("/taskList")
    fun getTaskList(): Single<Result<List<TaskResponse>>>

    @GET("/assignedTaskList")
    fun getAssignedTaskList(
        @Query("userId") userId: String,
        @Query("toDate") toDate: String,
        @Query("fromDate") fromDate: String
    ): Single<Result<List<AssignedTaskResponse>>>

}
package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.task.TaskApi
import daniel.rivero.homematters.data.datasource.api.task.model.AssignTaskRequest
import daniel.rivero.homematters.data.datasource.api.task.model.AssignedTaskResponse
import daniel.rivero.homematters.data.datasource.api.task.model.CustomTaskRequest
import daniel.rivero.homematters.data.datasource.api.task.model.TaskResponse
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.interactor.task.assign.AssignTaskDto
import daniel.rivero.homematters.domain.interactor.task.create.CreateTaskDto
import daniel.rivero.homematters.domain.interactor.task.list.AssignedTaskListToUserDto
import daniel.rivero.homematters.domain.repository.TaskRepository
import daniel.rivero.homematters.presentation.common.utils.parseDate
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class TaskDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : TaskRepository {

    companion object {
        const val DEFAULT_IMAGE_ICON = "ic_default_task"
    }

    private val api by lazy { clientGenerator.generateApi(TaskApi::class) }

    override fun getTaskList(): Single<List<Task>> {
        val response = api.getTaskList()

        return apiResponseHandler.processResponse(response).map { it.mapTaskListResponseToDomain() }
    }

    private fun List<TaskResponse>.mapTaskListResponseToDomain() = map { Task(it.id, it.name, it.icon) }

    override fun getAssignedTaskList(assignedTaskListToUserDto: AssignedTaskListToUserDto): Single<List<AssignedTask>> {
        val response = api.getAssignedTaskList(
            assignedTaskListToUserDto.userId,
            assignedTaskListToUserDto.toDate.time,
            assignedTaskListToUserDto.fromDate.time
        )

        return apiResponseHandler.processResponse(response).map { it.mapAssignedTaskListResponseToDomain() }
    }

    override fun createCustomTask(dto: CreateTaskDto): Completable {
        return api.createCustomTask(CustomTaskRequest(dto.name, dto.name))
    }

    override fun getCustomTaskList(homeId: String): Single<List<Task>> {
        val response = api.getCustomTasks(homeId)

        return apiResponseHandler.processResponse(response).map { it.mapTaskListResponseToDomain() }
    }

    override fun assignTask(dto: AssignTaskDto): Completable {
        return api.assignTask(buildAssignedTaskRequest(dto))
    }

    private fun buildAssignedTaskRequest(task: AssignTaskDto) = AssignTaskRequest(
        task.name, DEFAULT_IMAGE_ICON, task.effort.value, task.date.time, task.userId, task.homeId, task.isDone
    )

    private fun AssignedTaskResponse.mapToDomain() = AssignedTask(
        this.id,
        this.name,
        this.icon,
        TaskEffort.getEffortFromValue(this.effort) ?: TaskEffort.XS,
        this.date.parseDate(),
        this.isDone,
        this.userId,
        this.houseId
    )

    override fun updateAssignedTask(task: AssignedTask): Completable {
        return api.assignTask(buildAssignedTaskRequest(task))
    }

    private fun buildAssignedTaskRequest(task: AssignedTask) = AssignTaskRequest(
        task.name, task.image, task.effort.value, task.date.time, task.userId, task.homeId, task.isDone, task.id
    )

    private fun List<AssignedTaskResponse>.mapAssignedTaskListResponseToDomain() = map {
        AssignedTask(it.id, it.name, it.icon, TaskEffort.getEffortFromValue(it.effort) ?: TaskEffort.XS, it.date.parseDate(), it.isDone, it.userId, it.houseId)
    }

}
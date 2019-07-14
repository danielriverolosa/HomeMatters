package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.task.TaskApi
import daniel.rivero.homematters.data.datasource.api.task.model.AssignedTaskResponse
import daniel.rivero.homematters.data.datasource.api.task.model.TaskResponse
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.interactor.task.assign.AssignTaskDto
import daniel.rivero.homematters.domain.interactor.task.create.CreateTaskDto
import daniel.rivero.homematters.domain.interactor.task.list.AssignedTaskListToUserDto
import daniel.rivero.homematters.domain.interactor.task.update.UpdateAssignedTaskDto
import daniel.rivero.homematters.domain.repository.TaskRepository
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import io.reactivex.Completable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import timber.log.Timber
import java.text.ParseException


class TaskDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : TaskRepository {

    private val locale = Locale("es", "ES")

    override fun getTaskList(): Single<List<Task>> {
        val api = clientGenerator.generateApi(TaskApi::class)

        val response = api.getTaskList()

        return apiResponseHandler.processResponse(response).map { it.mapTaskListResponseToDomain() }
    }

    private fun List<TaskResponse>.mapTaskListResponseToDomain() = map { Task(it.id, it.name, it.icon) }

    override fun getAssignedTaskList(assignedTaskListToUserDto: AssignedTaskListToUserDto): Single<List<AssignedTask>> {
        val api = clientGenerator.generateApi(TaskApi::class)

        val response = api.getAssignedTaskList(
            assignedTaskListToUserDto.userId,
            formatDate(assignedTaskListToUserDto.toDate),
            formatDate(assignedTaskListToUserDto.fromDate)
        )

        return apiResponseHandler.processResponse(response).map { it.mapAssignedTaskListResponseToDomain() }
    }

    override fun createCustomTask(createTaskDto: CreateTaskDto): Completable {
        return Completable.complete()
    }

    override fun getCustomTaskList(homeId: String): Single<List<Task>> {
        return Single.just(listOf(Task("12345", "Mock", "ic_default_task")))
    }

    override fun assignTask(dto: AssignTaskDto): Completable {
        return Completable.complete()
    }

    override fun updateAssignedTask(dto: UpdateAssignedTaskDto): Completable {
        return Completable.complete()
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", locale)
        return formatter.format(date)
    }

    private fun List<AssignedTaskResponse>.mapAssignedTaskListResponseToDomain() = map {
        AssignedTask(it.id, it.name, it.icon, EffortResolver.getTaskEffort(it.effort), parseShortDate(it.date), it.isDone)
    }

    private fun parseShortDate(strDate: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", locale)

        try {
            return formatter.parse(strDate)
        } catch (e: ParseException) {
            Timber.d(e)
        }
        return Date()
    }

}
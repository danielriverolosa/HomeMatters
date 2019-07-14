package daniel.rivero.homematters.domain.repository

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.interactor.task.create.CreateTaskDto
import daniel.rivero.homematters.domain.interactor.task.list.AssignedTaskListToUserDto
import io.reactivex.Completable
import io.reactivex.Single

interface TaskRepository {

    fun getTaskList(): Single<List<Task>>

    fun getAssignedTaskList(assignedTaskListToUserDto: AssignedTaskListToUserDto): Single<List<AssignedTask>>

    fun createCustomTask(createTaskDto: CreateTaskDto): Completable

}
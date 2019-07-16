package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event
import java.util.*

sealed class TaskDetailEvent : Event {

    class Initialize(val task: Task): TaskDetailEvent()
    class EffortChange(val value: Int): TaskDetailEvent()
    class Continue(val task: Task, val taskEffort: TaskEffort?, val date: Date?, val assignedUser: User?, val isDone: Boolean = false): TaskDetailEvent()

}
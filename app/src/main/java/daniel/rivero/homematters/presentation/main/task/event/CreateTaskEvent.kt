package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event
import java.util.*

sealed class CreateTaskEvent: Event {
    object Initialize : CreateTaskEvent()
    class Continue(val name: String?, val date: Date?, val assignedUser: User?, val taskEffort: TaskEffort?, val isDone: Boolean = false): CreateTaskEvent()
    class EffortChange(val value: Int) : CreateTaskEvent()
}
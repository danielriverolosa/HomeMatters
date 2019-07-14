package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.presentation.base.ViewState
import daniel.rivero.homematters.presentation.main.task.model.WeeklyData

sealed class TaskManagerViewState: ViewState {

    class LoadData(val data: WeeklyData): TaskManagerViewState()

}
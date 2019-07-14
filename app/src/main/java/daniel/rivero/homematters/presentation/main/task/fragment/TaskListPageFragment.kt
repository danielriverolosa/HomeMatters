package daniel.rivero.homematters.presentation.main.task.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.*
import daniel.rivero.homematters.presentation.main.calendar.adapter.TaskListAdapter
import daniel.rivero.homematters.presentation.main.task.event.TaskListEvent
import daniel.rivero.homematters.presentation.main.task.viewmodel.TaskListViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListViewState
import kotlinx.android.synthetic.main.fragment_task_list.*

@ContentView(R.layout.fragment_task_list)
class TaskListPageFragment : BaseViewModelFragment<TaskListViewModel, TaskListViewState>() {

    companion object {
        fun getInstance(title: String, taskList: List<AssignedTask>) = TaskListPageFragment().apply {
            setParamsByClass(title)
            setParamByList(taskList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(TaskListEvent.Initialize(getParamByList()))
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: TaskListViewState) {
        when (viewState) {
            is TaskListViewState.LoadData -> loadInitialData(viewState.taskList)
        }
    }

    private fun loadInitialData(taskList: List<AssignedTask>) {
        if (taskList.isEmpty()) {
            recyclerView.hide()
            emptyView.show()
        } else {
            recyclerView.adapter = TaskListAdapter(taskList) { viewModel.onEvent(TaskListEvent.OnClickTask(it)) }
        }
    }

    fun getTitle() = getParamsByClass<String>()

}
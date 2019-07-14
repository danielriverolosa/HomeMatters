package daniel.rivero.homematters.presentation.main.task.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.getParamByList
import daniel.rivero.homematters.presentation.base.utils.getParamsByClass
import daniel.rivero.homematters.presentation.base.utils.setParamByList
import daniel.rivero.homematters.presentation.base.utils.setParamsByClass
import daniel.rivero.homematters.presentation.main.task.adapter.TaskListSelectorPageAdapter
import daniel.rivero.homematters.presentation.main.task.event.TaskListSelectorPageEvent
import daniel.rivero.homematters.presentation.main.task.viewmodel.TaskListSelectorPageViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListSelectorPageViewState
import kotlinx.android.synthetic.main.fragment_task_list.*

@ContentView(R.layout.fragment_task_list)
class TaskListSelectorPageFragment : BaseViewModelFragment<TaskListSelectorPageViewModel, TaskListSelectorPageViewState>() {

    companion object {
        fun getInstance(title: String, taskList: List<Task>) = TaskListSelectorPageFragment().apply {
            setParamsByClass(title)
            setParamByList(taskList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(TaskListSelectorPageEvent.Initialize(getParamByList()))
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: TaskListSelectorPageViewState) {
        when(viewState) {
            is TaskListSelectorPageViewState.LoadData -> loadInitialData(viewState.taskList)
        }
    }

    private fun loadInitialData(taskList: List<Task>) {
        recyclerView.adapter = TaskListSelectorPageAdapter(taskList) { viewModel.onEvent(TaskListSelectorPageEvent.OnClickTask(it)) }
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    fun getTitle() = getParamsByClass<String>()

}
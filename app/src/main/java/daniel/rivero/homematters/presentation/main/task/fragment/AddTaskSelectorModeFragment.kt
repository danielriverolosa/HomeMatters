package daniel.rivero.homematters.presentation.main.task.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.main.task.event.AddTaskSelectorModeEvent
import daniel.rivero.homematters.presentation.main.task.viewmodel.AddTaskSelectorModeViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.AddTaskSelectorModeViewState
import kotlinx.android.synthetic.main.fragment_add_task_selector_mode.*

@ContentView(R.layout.fragment_add_task_selector_mode)
class AddTaskSelectorModeFragment: BaseViewModelFragment<AddTaskSelectorModeViewModel, AddTaskSelectorModeViewState>() {

    companion object {
        fun getInstance() = AddTaskSelectorModeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(AddTaskSelectorModeEvent.Initialize)
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: AddTaskSelectorModeViewState) {
        when(viewState) {
            AddTaskSelectorModeViewState.InitializeView -> {
                newTaskButton.setOnClickListener { viewModel.onEvent(AddTaskSelectorModeEvent.CreateTask) }
                existingTaskButton.setOnClickListener { viewModel.onEvent(AddTaskSelectorModeEvent.ExistingTask) }
            }
        }
    }
}
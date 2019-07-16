package daniel.rivero.homematters.presentation.main.task.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.*
import daniel.rivero.homematters.presentation.common.fragment.DatePickerFragment
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.common.utils.formatDate
import daniel.rivero.homematters.presentation.common.utils.parseFormalDate
import daniel.rivero.homematters.presentation.main.home.adapter.UserArrayAdapter
import daniel.rivero.homematters.presentation.main.task.event.TaskDetailEvent
import daniel.rivero.homematters.presentation.main.task.viewmodel.TaskDetailViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskDetailViewState
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.util.*

@ContentView(R.layout.fragment_task_detail)
class TaskDetailFragment : BaseViewModelFragment<TaskDetailViewModel, TaskDetailViewState>() {

    companion object {
        fun getInstance(task: Task) = TaskDetailFragment().apply {
            setParamsByClass(task)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(TaskDetailEvent.Initialize(getParamsByClass()))
        continueButton.setOnClickListener { onEventContinue() }
        effortSeekBar.setOnSeekBarChangeListener { viewModel.onEvent(TaskDetailEvent.EffortChange(it)) }
        dateInput.setOnClickListener {
            val datePickerFragment = DatePickerFragment.getNewInstance()
            datePickerFragment.setListener(datePickerListener)
            activity?.supportFragmentManager?.let { datePickerFragment.show(it, DatePickerFragment::class.java.name) }
        }
    }

    private fun onEventContinue() {
        viewModel.onEvent(
            TaskDetailEvent.Continue(
                getParamsByClass(),
                EffortResolver.getTaskEffort(effortSeekBar.getProgress()),
                dateInput.text?.toString()?.parseFormalDate(),
                userAssignedSpinner.selectedItem as User?,
                isDoneSwitch.isChecked
            )
        )
    }

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        dateInput.setText(Date(calendar.timeInMillis).formatDate())
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: TaskDetailViewState) {
        when (viewState) {
            is TaskDetailViewState.LoadTaskData -> loadTaskData(viewState.task, viewState.userList)
            is TaskDetailViewState.LoadAssignedTaskData -> loadAssignedTaskData(viewState.task, viewState.userList)
            is TaskDetailViewState.OnEffortChange -> updateTaskEffort(viewState.effort)
            is TaskDetailViewState.ShowLoadingWhileSaving -> showLoadingWhileSaving()
            TaskDetailViewState.UserHasRequired -> userAssignedInputLayout.error = getString(R.string.general_required_field)
            TaskDetailViewState.DateHasRequired -> dateInputLayout.error = getString(R.string.general_required_field)
            is TaskDetailViewState.Close -> close()
            is TaskDetailViewState.ShowError -> {
                hideLoadingWhileSaving()
                showMessage(viewState.message ?: getString(R.string.general_something_went_wrong))
            }
        }
    }

    private fun showLoadingWhileSaving() {
        loadingView.show()
        continueButton.invisible()
    }

    private fun hideLoadingWhileSaving() {
        loadingView.hide()
        continueButton.show()
    }

    private fun loadTaskData(task: Task, userList: List<User>) {
        context?.let {
            nameText.text = task.name
            headerImage.setImageResource(getImageResourceFromString(task.image, it))

            userAssignedSpinner.adapter = UserArrayAdapter(
                it,
                userList,
                R.string.add_user_assigned_selection_text,
                R.string.add_user_assigned_selection_hint
            )
            effortSeekBar.setValuesSeekBar(0, 5, 1, EffortResolver.getProgress(TaskEffort.XS))
            effortSeekBar.setLimitValues(TaskEffort.XS.description, TaskEffort.XXL.description)

            effort.text = getString(R.string.edit_user_effort, TaskEffort.XS.description, TaskEffort.XS.value)
        }
        showContentView()
    }

    private fun loadAssignedTaskData(task: AssignedTask, userList: List<User>) {
        context?.let {
            nameText.text = task.name
            headerImage.setImageResource(getImageResourceFromString(task.image, it))

            userAssignedSpinner.adapter = UserArrayAdapter(
                it,
                userList,
                R.string.add_user_assigned_selection_text,
                R.string.add_user_assigned_selection_hint
            )
            effortSeekBar.setValuesSeekBar(0, 5, 1, EffortResolver.getProgress(task.effort))
            effortSeekBar.setLimitValues(TaskEffort.XS.description, TaskEffort.XXL.description)

            effort.text = getString(R.string.edit_user_effort, task.effort.description, task.effort.value)

            isDoneSwitch.isChecked = task.isDone
            dateInput.setText(task.date.formatDate())
            userAssignedSpinner.setSelection(userList.indexOf(userList.first { user -> user.id == task.userId }))
        }
        showContentView()
    }

    private fun updateTaskEffort(taskEffort: TaskEffort) {
        effort.text = getString(R.string.edit_user_effort, taskEffort.description, taskEffort.value)
    }

    private fun showContentView() {
        initLoadingView.hide()
        contentView.show()
    }

}
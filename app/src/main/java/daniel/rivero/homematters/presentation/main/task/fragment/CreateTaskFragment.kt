package daniel.rivero.homematters.presentation.main.task.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.hide
import daniel.rivero.homematters.presentation.base.utils.invisible
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.common.fragment.DatePickerFragment
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.common.utils.formatDate
import daniel.rivero.homematters.presentation.common.utils.parseFormalDate
import daniel.rivero.homematters.presentation.main.home.adapter.UserArrayAdapter
import daniel.rivero.homematters.presentation.main.task.event.CreateTaskEvent
import daniel.rivero.homematters.presentation.main.task.viewmodel.CreateTaskViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.CreateTaskViewState
import kotlinx.android.synthetic.main.fragment_create_task.*
import java.util.*

@ContentView(R.layout.fragment_create_task)
class CreateTaskFragment: BaseViewModelFragment<CreateTaskViewModel, CreateTaskViewState>() {

    companion object {
        fun getInstance() = CreateTaskFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(CreateTaskEvent.Initialize)
        continueButton.setOnClickListener { onEventContinue() }
        effortSeekBar.setOnSeekBarChangeListener { viewModel.onEvent(CreateTaskEvent.EffortChange(it)) }
        dateInput.setOnClickListener {
            val datePickerFragment = DatePickerFragment.getNewInstance()
            datePickerFragment.setListener(datePickerListener)
            activity?.supportFragmentManager?.let { datePickerFragment.show(it, DatePickerFragment::class.java.name) }
        }
    }

    private fun onEventContinue() {
        viewModel.onEvent(
            CreateTaskEvent.Continue(
                nameInput.text.toString(),
                dateInput.text.toString().parseFormalDate(),
                userAssignedSpinner.selectedItem as User?,
                EffortResolver.getTaskEffort(effortSeekBar.getProgress()),
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

    override fun render(viewState: CreateTaskViewState) {
        when(viewState) {
            is CreateTaskViewState.LoadInitialData -> loadData(viewState.userList)
            CreateTaskViewState.Loading -> showLoading()
            is CreateTaskViewState.OnEffortChange -> updateTaskEffort(viewState.effort)
            CreateTaskViewState.UserHasRequired -> userAssignedInputLayout.error = getString(R.string.general_required_field)
            CreateTaskViewState.NameHasRequired -> nameInputLayout.error = getString(R.string.general_required_field)
            CreateTaskViewState.DateHasRequired -> dateInputLayout.error = getString(R.string.general_required_field)
            CreateTaskViewState.Close -> close()
            is CreateTaskViewState.ShowError -> {
                hideLoading()
                showMessage(viewState.message ?: getString(R.string.general_something_went_wrong))
            }
        }
    }

    private fun loadData(userList: List<User>) {
        context?.let {
            userAssignedSpinner.adapter = UserArrayAdapter(it, userList, R.string.add_user_assigned_selection_text, R.string.add_user_assigned_selection_hint)
            effortSeekBar.setValuesSeekBar(0, 5, 1, EffortResolver.getProgress(TaskEffort.XS))
            effortSeekBar.setLimitValues(TaskEffort.XS.description, TaskEffort.XXL.description)

            effort.text = getString(R.string.edit_user_effort, TaskEffort.XS.description, TaskEffort.XS.value)
        }
        showContentView()
    }

    private fun showContentView() {
        initLoadingView.hide()
        contentView.show()
    }

    private fun showLoading() {
        continueButton.invisible()
        loadingView.show()
    }

    private fun hideLoading() {
        continueButton.show()
        loadingView.hide()
    }

    private fun updateTaskEffort(taskEffort: TaskEffort) {
        effort.text = getString(R.string.edit_user_effort, taskEffort.description, taskEffort.value)
    }

}
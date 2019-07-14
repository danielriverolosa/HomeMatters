package daniel.rivero.homematters.presentation.main.user.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.UserEffort
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.getParamsByClass
import daniel.rivero.homematters.presentation.base.utils.invisible
import daniel.rivero.homematters.presentation.base.utils.setParamsByClass
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.main.user.event.EditUserEvent
import daniel.rivero.homematters.presentation.main.user.viewmodel.EditUserViewModel
import daniel.rivero.homematters.presentation.main.user.viewstate.EditUserViewState
import kotlinx.android.synthetic.main.fragment_edit_user.*

@ContentView(R.layout.fragment_edit_user)
class EditUserFragment : BaseViewModelFragment<EditUserViewModel, EditUserViewState>() {

    companion object {
        fun getInstance(user: User) = EditUserFragment().apply {
            setParamsByClass(user)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(EditUserEvent.Initialize(getParamsByClass()))
        effortSeekBar.setOnSeekBarChangeListener { viewModel.onEvent(EditUserEvent.EffortChange(it)) }
        continueButton.setOnClickListener {
            viewModel.onEvent(
                EditUserEvent.Continue(
                    nameInput.text.toString(),
                    effortSeekBar.getProgress()
                )
            )
        }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: EditUserViewState) {
        when (viewState) {
            is EditUserViewState.LoadInitialData -> loadData(viewState.name, viewState.effort)
            EditUserViewState.Loading -> showLoading()
            is EditUserViewState.OnEffortChange -> updateUserEffort(viewState.effort)
            is EditUserViewState.EditUserError -> showMessage(viewState.message ?: getString(R.string.general_something_went_wrong))
            EditUserViewState.Close -> close()
        }
    }

    private fun loadData(name: String, effort: UserEffort?) {
        effortSeekBar.setValuesSeekBar(0, 5, 1, EffortResolver.getProgress(effort ?: UserEffort.XS))
        effortSeekBar.setLimitValues(UserEffort.XS.description, UserEffort.XXL.description)

        nameInput.setText(name)
        val totalEffort = effort ?: UserEffort.XS
        weeklyEffort.text = getString(R.string.edit_user_effort, totalEffort.description, totalEffort.value)
    }

    private fun showLoading() {
        continueButton.invisible()
        loadingView.show()
    }

    private fun updateUserEffort(effort: UserEffort) {
        weeklyEffort.text = getString(R.string.edit_user_effort, effort.description, effort.value)
    }

}
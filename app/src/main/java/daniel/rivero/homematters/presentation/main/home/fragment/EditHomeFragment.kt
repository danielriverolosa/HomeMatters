package daniel.rivero.homematters.presentation.main.home.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.*
import daniel.rivero.homematters.presentation.main.home.adapter.UserArrayAdapter
import daniel.rivero.homematters.presentation.main.home.event.EditHomeEvent
import daniel.rivero.homematters.presentation.main.home.viewmodel.EditHomeViewModel
import daniel.rivero.homematters.presentation.main.home.viewstate.EditHomeViewState
import kotlinx.android.synthetic.main.fragment_edit_home.*
import java.util.*

@ContentView(R.layout.fragment_edit_home)
class EditHomeFragment: BaseViewModelFragment<EditHomeViewModel, EditHomeViewState>() {

    companion object {
        fun getInstance(userList: List<User>, home: Home) = EditHomeFragment().apply {
            setParamByList(userList)
            setParamsByClass(home)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(EditHomeEvent.LoadData(getParamsByClass(), getParamByList()))
        continueButton.setOnClickListener {
            viewModel.onEvent(EditHomeEvent.Continue(nameInput.text.toString(), adminSpinner.selectedItem as User))
        }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: EditHomeViewState) {
        when(viewState) {
            EditHomeViewState.Loading -> showLoading()
            is EditHomeViewState.LoadData -> loadData(viewState.homeName, viewState.admin, viewState.userList)
        }
    }

    private fun showLoading() {
        continueButton.invisible()
        loadingView.show()
    }

    private fun loadData(homeName: String, admin: User, userList: List<User>) {
        context?.let {
            nameInput.setText(homeName)
            adminSpinner.adapter = UserArrayAdapter(it, userList, 0, R.string.admin_spinner_title_text)
            adminSpinner.setSelection(userList.indexOf(admin))
        }
    }

}
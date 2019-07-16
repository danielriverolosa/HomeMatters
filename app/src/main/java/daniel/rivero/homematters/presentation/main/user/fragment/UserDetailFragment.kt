package daniel.rivero.homematters.presentation.main.user.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.UserEffort
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.*
import daniel.rivero.homematters.presentation.main.user.event.UserDetailEvent
import daniel.rivero.homematters.presentation.main.user.viewmodel.UserDetailViewModel
import daniel.rivero.homematters.presentation.main.user.viewstate.UserDetailViewState
import kotlinx.android.synthetic.main.fragment_user_detail.*

@ContentView(R.layout.fragment_user_detail)
class UserDetailFragment: BaseViewModelFragment<UserDetailViewModel, UserDetailViewState>() {

    companion object {
        fun getInstance(user: User, showEditMode: Boolean) = UserDetailFragment().apply {
            setParamsByClass(showEditMode)
            setParamsByClass(user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(UserDetailEvent.Initialize(getParamsByClass()))
        deleteHomeButton.setOnClickListener { viewModel.onEvent(UserDetailEvent.DeleteHome) }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val showEditMode = getParamsByClass<Boolean>()
        if (showEditMode) {
            inflater.inflate(R.menu.menu_user_detail, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_edit_account -> {
            viewModel.onEvent(UserDetailEvent.EditAccount)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun render(viewState: UserDetailViewState) {
        when(viewState) {
            is UserDetailViewState.LoadData -> loadInitialData(viewState.user, viewState.isAdmin)
            UserDetailViewState.DeleteHomeLoading -> showLoading()
            UserDetailViewState.HomeDeleted -> hideHomeOptions()
            is UserDetailViewState.GenericError -> showMessage(viewState.message ?: getString(R.string.general_something_went_wrong))
        }
    }

    private fun loadInitialData(user: User, admin: Boolean) {
        userText.text = user.name
        emailtext.text = user.email
        val effort = UserEffort.getEffortFromValue(user.weeklyEffort)
        effortText.text = context?.getString(R.string.edit_user_effort, effort?.description, effort?.value)

        user.homeId?.let {
            manageUserView(admin)
        } ?: emptyHomeGroup.show()
    }

    private fun manageUserView(admin: Boolean) {
        emptyHomeGroup.hide()
        if (admin) {
            deleteHomeButton.show()
        } else {
            noAdminView.show()
        }
    }

    private fun showLoading() {
        deleteHomeButton.invisible()
        deleteHomeLoadingView.show()
    }

    private fun hideHomeOptions() {
        deleteHomeButton.invisible()
        deleteHomeLoadingView.hide()
        emptyHomeGroup.show()
    }

}
package daniel.rivero.homematters.presentation.main.home.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.*
import daniel.rivero.homematters.presentation.main.home.event.AddUserEvent
import daniel.rivero.homematters.presentation.main.home.viewmodel.AddUserViewModel
import daniel.rivero.homematters.presentation.main.home.viewstate.AddUserViewState
import kotlinx.android.synthetic.main.fragment_add_user.*

@ContentView(R.layout.fragment_add_user)
class AddUserFragment : BaseViewModelFragment<AddUserViewModel, AddUserViewState>() {

    companion object {
        fun getInstance(home: Home) = AddUserFragment().apply {
            setParamsByClass(home)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener { viewModel.onEvent(AddUserEvent.FindUser(searchView.query.toString())) }
        addUserButton.setOnClickListener { viewModel.onEvent(AddUserEvent.AddUser(emailtext.text.toString(), getParamsByClass())) }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: AddUserViewState) {
        when (viewState) {
            is AddUserViewState.LoadUserData -> loadUserData(viewState.user)
            AddUserViewState.UserAdded -> showSuccessView()
            AddUserViewState.UserNotFound -> showWarningView(R.string.add_user_not_found_text)
            AddUserViewState.UserAlreadyExists -> showWarningView(R.string.add_user_already_exist_text)
            AddUserViewState.GenericError -> showWarningView(R.string.general_something_went_wrong)
            AddUserViewState.SearchLoading -> showSearchLoading()
            AddUserViewState.AddUserLoading -> showAddUserLoading()
        }
    }

    private fun loadUserData(user: User) {
        hideSearchLoading()

        userText.text = user.name
        emailtext.text = user.email
        effortText.text = context?.getString(R.string.add_user_effort, user.weeklyEffort)
        userCardView.show()
        warningCardView.hide()
    }

    private fun showSuccessView() {
        searchButton.invisible()
        searchLoadingView.hide()
        successImage.show()
    }

    private fun showWarningView(@StringRes message: Int) {
        warningText.setText(message)
        userCardView.hide()
        warningCardView.show()
    }

    private fun showSearchLoading() {
        searchButton.invisible()
        searchLoadingView.show()
        userCardView.hide()
        warningCardView.hide()
        resetAddUserCard()
    }

    private fun hideSearchLoading() {
        searchButton.show()
        searchLoadingView.hide()
    }

    private fun showAddUserLoading() {
        addUserButton.invisible()
        addUserLoadingView.show()
        successImage.invisible()
    }

    private fun resetAddUserCard() {
        addUserButton.show()
        addUserLoadingView.hide()
        successImage.invisible()
    }

}
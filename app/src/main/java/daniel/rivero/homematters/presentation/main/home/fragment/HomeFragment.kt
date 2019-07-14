package daniel.rivero.homematters.presentation.main.home.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.getParamsByClass
import daniel.rivero.homematters.presentation.base.utils.hide
import daniel.rivero.homematters.presentation.base.utils.setParamsByClass
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.main.home.adapter.UserListAdapter
import daniel.rivero.homematters.presentation.main.home.event.HomeEvent
import daniel.rivero.homematters.presentation.main.home.viewmodel.HomeViewModel
import daniel.rivero.homematters.presentation.main.home.viewstate.HomeViewState
import kotlinx.android.synthetic.main.fragment_home.*

@ContentView(R.layout.fragment_home)
class HomeFragment : BaseViewModelFragment<HomeViewModel, HomeViewState>() {

    companion object {
        fun getInstance(home: Home) = HomeFragment().apply {
            setParamsByClass(home)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(HomeEvent.LoadData(getParamsByClass()))
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            viewModel.onEvent(HomeEvent.AddUser(getParamsByClass()))
            true
        }
        R.id.action_edit -> {
            viewModel.onEvent(HomeEvent.EditHomeData(getParamsByClass()))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun render(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.LoadHomeData -> loadHomeData(viewState.home)
            is HomeViewState.LoadUsersData -> loadData(viewState.userList, viewState.totalEffort)
        }
    }

    private fun loadHomeData(home: Home) {
        title.text = home.name
        appBarLayout.show()
    }

    private fun loadData(userList: List<User>, totalEffort: Int) {
        weeklyEffort.text = totalEffort.toString()
        weeklyEffort.show()

        recyclerView.show()
        loadingView.hide()
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = UserListAdapter(userList, ::onClickUser)
    }

    private fun onClickUser(user: User) {
        viewModel.onEvent(HomeEvent.ClickUser(user, getParamsByClass()))
    }

}
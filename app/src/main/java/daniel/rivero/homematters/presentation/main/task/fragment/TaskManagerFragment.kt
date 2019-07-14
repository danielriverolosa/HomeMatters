package daniel.rivero.homematters.presentation.main.task.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.hide
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.main.task.adapter.TaskManagerPagerAdapter
import daniel.rivero.homematters.presentation.main.task.event.TaskManagerEvent
import daniel.rivero.homematters.presentation.main.task.model.WeeklyData
import daniel.rivero.homematters.presentation.main.task.viewmodel.TaskManagerViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskManagerViewState
import kotlinx.android.synthetic.main.fragment_task_manager.*

@ContentView(R.layout.fragment_task_manager)
class TaskManagerFragment : BaseViewModelFragment<TaskManagerViewModel, TaskManagerViewState>() {

    companion object {
        fun getInstance() = TaskManagerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(TaskManagerEvent.Initialize)
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_add_task  -> {
            viewModel.onEvent(TaskManagerEvent.AddTask)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun render(viewState: TaskManagerViewState) {
        when (viewState) {
            is TaskManagerViewState.LoadData -> loadInitialData(viewState.data)
        }
    }

    private fun loadInitialData(data: WeeklyData) {
        loadViewPager(data)
        effortExpendedText.text = getString(R.string.user_list_expended_effort_info, data.expendedEffort)
        effortPendingText.text = getString(R.string.user_list_pending_effort_info, data.pendingEffort)

        appBarLayout.show()
        viewPager.show()
        loadingView.hide()
    }

    private fun loadViewPager(data: WeeklyData) {
        val pagerAdapter = TaskManagerPagerAdapter(activity as FragmentActivity)
        pagerAdapter.addPage(TaskListPageFragment.getInstance("Completadas", data.expendedTask))
        pagerAdapter.addPage(TaskListPageFragment.getInstance("Pendientes", data.pendingTask))

        viewPager.apply {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(tabLayout, viewPager, true) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()
    }
}
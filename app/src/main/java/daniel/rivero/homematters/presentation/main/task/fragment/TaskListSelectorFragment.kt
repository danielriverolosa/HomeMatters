package daniel.rivero.homematters.presentation.main.task.fragment

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.base.utils.hide
import daniel.rivero.homematters.presentation.base.utils.show
import daniel.rivero.homematters.presentation.main.task.adapter.TaskListSelectorPagerAdapter
import daniel.rivero.homematters.presentation.main.task.viewmodel.TaskListSelectorViewModel
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListSelectorViewState
import kotlinx.android.synthetic.main.fragment_task_list_selector.*

@ContentView(R.layout.fragment_task_list_selector)
class TaskListSelectorFragment: BaseViewModelFragment<TaskListSelectorViewModel, TaskListSelectorViewState>() {

    companion object {
        fun getInstance() = TaskListSelectorFragment()
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: TaskListSelectorViewState) {
        when(viewState) {
            is TaskListSelectorViewState.LoadData -> loadInitialData(viewState.defaultList, viewState.customList)
            is TaskListSelectorViewState.ShowError -> showMessage(viewState.message ?: getString(R.string.general_something_went_wrong))
        }
    }

    private fun loadInitialData(defaultList: List<Task>, customList: List<Task>) {
        loadViewPager(defaultList, customList)
        appBarLayout.show()
        viewPager.show()
        loadingView.hide()
    }

    private fun loadViewPager(defaultList: List<Task>, customList: List<Task>) {
        val pagerAdapter = TaskListSelectorPagerAdapter(activity as FragmentActivity)
        pagerAdapter.addPage(TaskListSelectorPageFragment.getInstance("Sugeridas", defaultList))
        pagerAdapter.addPage(TaskListSelectorPageFragment.getInstance("Personalizadas", customList))

        viewPager.apply {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(tabLayout, viewPager, true) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()
    }

}
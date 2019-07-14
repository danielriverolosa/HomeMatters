package daniel.rivero.homematters.presentation.main.task.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.presentation.main.task.fragment.TaskListSelectorPageFragment

class TaskListSelectorPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    val items = ArrayList<TaskListSelectorPageFragment>()

    override fun createFragment(position: Int) = items[position]

    override fun getItemCount() = items.size

    fun addPage(page: TaskListSelectorPageFragment) {
        items.add(page)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getPageTitle(position: Int) = items[position].getTitle()

    fun updateData(taskList: List<Task>) {
        items
    }

}
package daniel.rivero.homematters.presentation.main.task.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import daniel.rivero.homematters.presentation.main.task.fragment.TaskListPageFragment

class TaskManagerPagerAdapter(
    private val activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    val items = ArrayList<TaskListPageFragment>()

    override fun createFragment(position: Int) = items[position]

    override fun getItemCount() = items.size

    fun addPage(page: TaskListPageFragment) {
        items.add(page)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getPageTitle(position: Int) = items[position].getTitle()

}
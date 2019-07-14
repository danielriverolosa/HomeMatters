package daniel.rivero.homematters.presentation.main.task.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.presentation.base.utils.getImageResourceFromString
import daniel.rivero.homematters.presentation.base.utils.inflate
import kotlinx.android.synthetic.main.item_selector_task.view.*

class TaskListSelectorPageAdapter(
    private val taskList: List<Task>,
    private val onClickItem: (Task) -> Unit
): RecyclerView.Adapter<TaskListSelectorPageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(parent.inflate(R.layout.item_selector_task, false)) {
            ViewHolder(this, onClickItem)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        taskList[position].run(holder::bind)
    }

    override fun getItemCount() = taskList.size


    inner class ViewHolder(
        itemView: View,
        private val onClickItem: (Task) -> Unit
    ): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Task) {
            itemView.apply {
                name.text = item.name
                taskImage.setImageResource(getImageResourceFromString(item.image, context))
                setOnClickListener { onClickItem(item) }
            }
        }

    }

}
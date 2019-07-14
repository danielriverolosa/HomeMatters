package daniel.rivero.homematters.presentation.main.calendar.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.presentation.base.utils.getImageResourceFromString
import daniel.rivero.homematters.presentation.base.utils.inflate
import kotlinx.android.synthetic.main.item_calendar_task.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(
    private val taskList: List<AssignedTask>,
    private val onClickItem: (AssignedTask) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(parent.inflate(R.layout.item_calendar_task, false)) {
            ViewHolder(this, onClickItem)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        taskList[position].run(holder::bind)
    }

    override fun getItemCount() = taskList.size

    inner class ViewHolder(itemView: View, onClickItem: (AssignedTask) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AssignedTask) {
            itemView.apply {
                name.text = item.name
                taskImage.setImageResource(getImageResourceFromString(item.image, context))
                effort.text = item.effort.value.toString()
                effort.background = context.getDrawable(getBackgroundEffort(item.effort))
                date.text = formatDate(item.date)
                status.setText(getStatus(item.isDone))
                setOnClickListener { onClickItem(item) }
            }
        }

        private fun formatDate(date: Date): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
            return formatter.format(date)
        }

        private fun getBackgroundEffort(taskEffort: TaskEffort): Int {
            return when(taskEffort) {
                TaskEffort.XS -> R.drawable.bg_task_effort_xs
                TaskEffort.S -> R.drawable.bg_task_effort_s
                TaskEffort.M -> R.drawable.bg_task_effort_m
                TaskEffort.L -> R.drawable.bg_task_effort_l
                TaskEffort.XL -> R.drawable.bg_task_effort_xl
                TaskEffort.XXL -> R.drawable.bg_task_effort_xxl
            }
        }

        private fun getStatus(isDone: Boolean): Int {
            return if (isDone) {
                R.string.task_status_done
            } else {
                R.string.task_status_pending
            }
        }

    }

}
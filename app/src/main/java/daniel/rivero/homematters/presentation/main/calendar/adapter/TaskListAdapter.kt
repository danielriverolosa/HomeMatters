package daniel.rivero.homematters.presentation.main.calendar.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.presentation.base.utils.getImageResourceFromString
import daniel.rivero.homematters.presentation.base.utils.inflate
import kotlinx.android.synthetic.main.item_calendar_task.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(
    private val taskList: List<AssignedTask>
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(parent.inflate(R.layout.item_calendar_task, false)) {
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        taskList[position].run(holder::bind)
    }

    override fun getItemCount() = taskList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AssignedTask) {
            itemView.apply {
                name.text = item.name
                taskImage.setImageResource(getImageResourceFromString(item.image, itemView.context))
                effort.text = item.effort.toString()
                date.text = formatDate(item.date)
                status.setText(getStatus(item.isDone))
            }
        }
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
        return formatter.format(date)
    }

    private fun getStatus(isDone: Boolean): Int {
        return if (isDone) {
            R.string.task_status_done
        } else {
            R.string.task_status_pending
        }
    }

}
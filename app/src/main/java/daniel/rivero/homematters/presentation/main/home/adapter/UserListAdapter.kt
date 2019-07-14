package daniel.rivero.homematters.presentation.main.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.utils.inflate
import kotlinx.android.synthetic.main.item_calendar_task.view.*
import kotlinx.android.synthetic.main.item_user_list.view.*
import kotlinx.android.synthetic.main.item_user_list.view.name

class UserListAdapter(
    private val userList: List<User>,
    private val onClickUser: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(parent.inflate(R.layout.item_user_list, false)) {
            ViewHolder(this, onClickUser)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userList[position].run(holder::bind)
    }

    override fun getItemCount() = userList.size

    inner class ViewHolder(
        itemView: View,
        val onClickUser: (User) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) {
            itemView.setOnClickListener { onClickUser(item) }
            itemView.apply {
                name.text = item.name
                email.text = item.email
                effort.text = item.weeklyEffort.toString()
            }
        }
    }

}
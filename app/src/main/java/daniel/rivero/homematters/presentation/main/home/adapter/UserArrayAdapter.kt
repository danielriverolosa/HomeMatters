package daniel.rivero.homematters.presentation.main.home.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import kotlinx.android.synthetic.main.view_spinner_material.view.*

class UserArrayAdapter(
    context: Context,
    private val userList: List<User>,
    @StringRes private val title: Int,
    @StringRes private val hintText: Int
): ArrayAdapter<User>(context, 0, userList) {

    @LayoutRes
    private var itemDropdown = R.layout.item_spinner_dropdown

    override fun getItem(position: Int) = userList[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = userList.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?.tag as View?
            ?: LayoutInflater.from(context).inflate(
                R.layout.view_spinner_material,
                parent, false
            ) as View

        view.tag = view

        view.titleEditText.text = context.getString(title)
        view.editText.hint = context.getString(hintText)
        val user = getItem(position)
        view.editText.text = user.name

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(parent.context).inflate(itemDropdown, parent, false)

        val user = userList[position]

        val label = view.findViewById<TextView>(R.id.description)
        label.text = formatHtml(context.getString(R.string.user_spinner_dropdown_description, user.name, user.email))

        return view
    }

    private fun formatHtml(text: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(text)
        }
    }

}

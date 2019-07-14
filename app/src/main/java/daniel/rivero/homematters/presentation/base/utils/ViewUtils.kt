package daniel.rivero.homematters.presentation.base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(
    context).inflate(layoutRes, this, attachToRoot)

fun AppCompatEditText.afterTextChanged(afterTextChanged: ((String) -> Unit)?) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun AppCompatEditText.validate(validator: (String) -> Boolean, message: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }

    this.error = if (validator(this.text.toString())) null else message
}

fun SearchView.onQueryTextChange(queryTextChange: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                queryTextChange.invoke(newText)
            }
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean { return false }
    })
}

fun getImageResourceFromString(drawableName: String, context: Context): Int {
    return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
}
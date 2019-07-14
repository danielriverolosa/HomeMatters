package daniel.rivero.homematters.presentation.base.utils

import android.content.Intent
import android.os.Bundle
import daniel.rivero.homematters.presentation.base.*
import java.io.Serializable


//region BaseActivity extensions
inline fun <reified T : Serializable> Intent.setParamsByClass(obj: T, tag: String = "") {
    putExtra(T::class.java.name + tag, obj)
}

inline fun <reified T : Serializable> BaseActivity<*, *>.getParamsByClass(tag: String = ""): T =
    getIntent()?.extras?.get(T::class.java.name + tag) as T
//endregion

//region BaseViewModelFragment extensions
inline fun <reified T : Serializable> BaseFragment.setParamsByClass(
    obj: T,
    tag: String = ""
) {
    if (getArguments() == null) {
        setArguments(Bundle())
    }
    getArguments()?.putSerializable(T::class.java.name + tag, obj)
}

inline fun <reified T : Serializable> BaseFragment.getParamsByClass(tag: String = ""): T =
    arguments?.getSerializable(T::class.java.name + tag) as T

inline fun <reified T : Serializable> BaseFragment.setParamByList(obj: List<T>?, tag: String = "") {
    if (arguments == null) {
        arguments = Bundle()
    }
    arguments?.putSerializable(T::class.java.name + tag, obj as Serializable)
}

inline fun <reified T : Serializable> BaseFragment.getParamByList(tag: String = ""): List<T> =
    arguments?.getSerializable(T::class.java.name + tag) as List<T>

//endregion
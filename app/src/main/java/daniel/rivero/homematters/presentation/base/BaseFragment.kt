package daniel.rivero.homematters.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.infrastructure.di.module.ViewModule

abstract class BaseFragment: Fragment() {

    private val fragmentComponent by lazy {
        (activity?.application as AndroidApplication)
            .applicationComponent
            .viewComponent(ViewModule(activity as BaseActivity<*, *>))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(fragmentComponent)
    }

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewId = getFragmentContentView()
        if (viewId == 0) return null

        return inflater.inflate(viewId, container, false)
    }

    private fun getFragmentContentView(): Int {
        val annotation = javaClass.getAnnotation(ContentView::class.java)
        return annotation?.value ?: 0
    }

    fun showMessage(message: String) {
        (activity as BaseActivity<*, *>).showMessage(message)
    }

    fun close() {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

}
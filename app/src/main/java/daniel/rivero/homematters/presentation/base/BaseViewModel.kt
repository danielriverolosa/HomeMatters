package daniel.rivero.homematters.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseViewModel<VS: ViewState, E: Event>(app: Application): AndroidViewModel(app) {

    @Inject
    protected lateinit var navigator: Navigator

    protected val disposables = CompositeDisposable()

    private val mutableViewState = MutableLiveData<VS>()

    val viewState: LiveData<VS>
        get() = mutableViewState

    protected fun updateViewState(viewState: VS) {
        mutableViewState.value = viewState
    }

    abstract fun onEvent(event: E)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
package daniel.rivero.homematters.domain.interactor.task.list.custom

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class GetCustomTaskListByHome @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(home: Home?): Single<List<Task>> {
        home?.let {
            return repository.getCustomTaskList(it.id)
                .doOnError { t -> Timber.e(t.message) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        } ?: return Single.just(emptyList())
    }

}
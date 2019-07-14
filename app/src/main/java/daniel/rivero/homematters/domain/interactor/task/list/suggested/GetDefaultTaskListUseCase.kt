package daniel.rivero.homematters.domain.interactor.task.list.suggested

import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class GetDefaultTaskListUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(): Single<List<Task>> {
        return repository.getTaskList()
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
package daniel.rivero.homematters.domain.interactor.task.list

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class GetAssignedTaskListToSingleUser @Inject constructor(
    val repository: TaskRepository
) {

    operator fun invoke(params: AssignedTaskListToUserDto): Single<List<AssignedTask>> {
        return repository.getAssignedTaskList(params)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
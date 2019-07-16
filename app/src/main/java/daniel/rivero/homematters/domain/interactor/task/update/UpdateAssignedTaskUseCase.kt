package daniel.rivero.homematters.domain.interactor.task.update

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UpdateAssignedTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(task: AssignedTask): Completable {
        return repository.updateAssignedTask(task)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
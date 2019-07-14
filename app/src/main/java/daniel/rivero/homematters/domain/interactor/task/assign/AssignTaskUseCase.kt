package daniel.rivero.homematters.domain.interactor.task.assign

import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AssignTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(dto: AssignTaskDto): Completable {
        return repository.assignTask(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
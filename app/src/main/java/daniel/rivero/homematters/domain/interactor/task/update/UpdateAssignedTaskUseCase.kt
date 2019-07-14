package daniel.rivero.homematters.domain.interactor.task.update

import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UpdateAssignedTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(dto: UpdateAssignedTaskDto): Completable {
        return repository.updateAssignedTask(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
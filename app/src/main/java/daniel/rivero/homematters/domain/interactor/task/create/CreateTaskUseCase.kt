package daniel.rivero.homematters.domain.interactor.task.create

import daniel.rivero.homematters.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(createTaskDto: CreateTaskDto): Completable {
        return taskRepository.createCustomTask(createTaskDto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
package daniel.rivero.homematters.domain.interactor.home.delete

import daniel.rivero.homematters.domain.repository.HomeRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class DeleteHomeToUserUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    operator fun invoke(dto: DeleteHomeToUserDto): Completable {
        return repository.deleteHomeToUser(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
package daniel.rivero.homematters.domain.interactor.user.edit

import daniel.rivero.homematters.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class EditUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(dto: EditUserDto): Completable {
        return repository.editUser(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
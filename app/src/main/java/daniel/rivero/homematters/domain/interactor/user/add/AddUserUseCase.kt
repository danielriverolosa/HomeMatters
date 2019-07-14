package daniel.rivero.homematters.domain.interactor.user.add

import daniel.rivero.homematters.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(dto: AddUserDto): Completable {
        return repository.addUserToHome(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
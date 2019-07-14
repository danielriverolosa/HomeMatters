package daniel.rivero.homematters.domain.interactor.user.find

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FindUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(dto: FindUserDto): Single<User> {
        return repository.getUserByEmail(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
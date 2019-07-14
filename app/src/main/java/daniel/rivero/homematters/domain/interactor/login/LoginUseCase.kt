package daniel.rivero.homematters.domain.interactor.login

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.repository.SecurityRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: SecurityRepository
) {

    operator fun invoke(params: LoginDto): Single<User> {
        return repository.login(params)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
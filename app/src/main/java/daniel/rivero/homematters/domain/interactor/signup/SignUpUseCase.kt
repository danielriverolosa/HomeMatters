package daniel.rivero.homematters.domain.interactor.signup

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.repository.SecurityRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val securityRepository: SecurityRepository
) {

    operator fun invoke(signUpDto: SignUpDto): Single<User> {
        return securityRepository.signUp(signUpDto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
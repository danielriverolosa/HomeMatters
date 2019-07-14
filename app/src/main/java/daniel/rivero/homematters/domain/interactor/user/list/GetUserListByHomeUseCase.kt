package daniel.rivero.homematters.domain.interactor.user.list

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class GetUserListByHomeUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(home: Home): Single<List<User>> {
        return repository.getUserListByHome(home)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
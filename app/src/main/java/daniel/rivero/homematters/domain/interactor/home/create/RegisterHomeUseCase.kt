package daniel.rivero.homematters.domain.interactor.home.create

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.repository.HomeRepository
import daniel.rivero.homematters.domain.service.PreferenceService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RegisterHomeUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val preferenceService: PreferenceService
) {

    operator fun invoke(dto: RegisterHomeDto): Single<Home> {
        dto.admin = preferenceService.getUser()
        return repository.registerHome(dto)
            .doOnSuccess { house -> preferenceService.setDefaultHome(house) }
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
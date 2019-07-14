package daniel.rivero.homematters.domain.interactor.home.edit

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.repository.HomeRepository
import daniel.rivero.homematters.domain.service.PreferenceService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class EditHomeUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val preferenceService: PreferenceService
) {

    operator fun invoke(dto: EditHomeDto): Single<Home> {
        return repository.editHome(dto)
            .doOnSuccess { preferenceService.setDefaultHome(it) }
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
package daniel.rivero.homematters.domain.interactor.chart

import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.domain.repository.StatsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BarChartUseCase @Inject constructor(
    private val repository: StatsRepository
) {

    operator fun invoke(dto: BarChartDto): Single<List<EffortStat>> {
        return repository.getStatsByHome(dto)
            .doOnError { Timber.e(it.message) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
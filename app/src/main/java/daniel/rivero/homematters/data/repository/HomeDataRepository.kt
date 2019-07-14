package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.home.edit.EditHomeDto
import daniel.rivero.homematters.domain.interactor.home.create.RegisterHomeDto
import daniel.rivero.homematters.domain.interactor.home.delete.DeleteHomeToUserDto
import daniel.rivero.homematters.domain.repository.HomeRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : HomeRepository {

    override fun registerHome(dto: RegisterHomeDto): Single<Home> {
        return Single.create { buildHome() }
    }

    override fun editHome(dto: EditHomeDto): Single<Home> {
        return Single.create { buildHome() }
    }

    override fun deleteHomeToUser(dto: DeleteHomeToUserDto): Completable {
        return Completable.complete()
    }

    private fun buildHome() {
        Home("12345", "Torrejón", User("12345", "Daniel", "danielrl.drl@gmail.com", weeklyEffort = 10))
    }

}
package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.home.HomeApi
import daniel.rivero.homematters.data.datasource.api.user.UserApi
import daniel.rivero.homematters.data.datasource.api.user.model.HomeRequest
import daniel.rivero.homematters.data.datasource.api.user.model.UserRequest
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.Home
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

    val api by lazy { clientGenerator.generateApi(HomeApi::class) }

    override fun registerHome(dto: RegisterHomeDto): Single<Home> {
        val response = api.editHouse(HomeRequest(dto.name, dto.admin?.id))

        return apiResponseHandler.processResponse(response).map { Home(it.id, it.name, it.adminId) }
    }

    override fun editHome(dto: EditHomeDto): Single<Home> {
        val response = api.editHouse(HomeRequest(dto.newName, dto.admin.id, dto.id))
        return apiResponseHandler.processResponse(response).map { Home(it.id, it.name, it.adminId) }
    }

    override fun deleteHomeToUser(dto: DeleteHomeToUserDto): Completable {
        val userApi = clientGenerator.generateApi(UserApi::class)

        return userApi.updateUser(UserRequest(dto.user.id, dto.user.name, dto.user.email, null, dto.user.weeklyEffort))
    }

}
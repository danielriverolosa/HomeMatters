package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.user.UserApi
import daniel.rivero.homematters.data.datasource.api.user.model.UpdateHomeRequest
import daniel.rivero.homematters.data.datasource.api.user.model.UserRequest
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.UserEffort
import daniel.rivero.homematters.domain.interactor.user.add.AddUserDto
import daniel.rivero.homematters.domain.interactor.user.edit.EditUserDto
import daniel.rivero.homematters.domain.interactor.user.find.FindUserDto
import daniel.rivero.homematters.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : UserRepository {

    private val api by lazy { clientGenerator.generateApi(UserApi::class) }

    override fun getUserListByHome(homeId: String): Single<List<User>> {
        val response = api.getUsersByHouseId(homeId)

        return apiResponseHandler.processResponse(response).map {
            it.map { user -> User(user.id, user.name, user.email, user.houseId, user.effort ?: UserEffort.XS.value) }
        }
    }

    override fun getUserByEmail(findUserDto: FindUserDto): Single<User?> {
        val response = api.getUsersEmail(findUserDto.email)
        return apiResponseHandler.processResponse(response).map {
            it.map { user -> User(user.id, user.name, user.email, user.houseId, user.effort ?: UserEffort.XS.value) }.firstOrNull()
        }
    }

    override fun addUserToHome(dto: AddUserDto): Completable {
        return api.updateHome(UpdateHomeRequest(dto.userId, dto.home.id, dto.email))
    }

    override fun editUser(dto: EditUserDto): Completable {
        return api.updateUser(UserRequest(dto.user.id, dto.name, dto.user.email, dto.user.homeId, dto.effort.value))
    }

}
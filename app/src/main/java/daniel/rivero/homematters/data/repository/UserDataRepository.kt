package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
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
    override fun getUserListByHome(home: Home): Single<List<User>> {
        return Single.create{ listOf(buildUser()) }
    }

    override fun getUserByEmail(findUserDto: FindUserDto): Single<User> {
        return Single.create { buildUser() }
    }

    override fun addUserToHome(addUserDto: AddUserDto): Completable {
        return Completable.complete()
    }

    override fun editUser(editUserDto: EditUserDto): Completable {
        return Completable.complete()
    }

    private fun buildUser() = User("12345", "Daniel", "danielrl.drl@gmail.com", weeklyEffort = 10)

}
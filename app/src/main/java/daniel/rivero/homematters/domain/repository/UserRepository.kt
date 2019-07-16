package daniel.rivero.homematters.domain.repository

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.user.add.AddUserDto
import daniel.rivero.homematters.domain.interactor.user.edit.EditUserDto
import daniel.rivero.homematters.domain.interactor.user.find.FindUserDto
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun getUserListByHome(homeId: String): Single<List<User>>

    fun getUserByEmail(findUserDto: FindUserDto): Single<User?>

    fun addUserToHome(dto: AddUserDto): Completable

    fun editUser(dto: EditUserDto): Completable

}
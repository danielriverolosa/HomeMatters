package daniel.rivero.homematters.domain.repository

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.interactor.home.edit.EditHomeDto
import daniel.rivero.homematters.domain.interactor.home.create.RegisterHomeDto
import daniel.rivero.homematters.domain.interactor.home.delete.DeleteHomeToUserDto
import io.reactivex.Completable
import io.reactivex.Single

interface HomeRepository {

    fun registerHome(dto: RegisterHomeDto): Single<Home>

    fun editHome(dto: EditHomeDto): Single<Home>

    fun deleteHomeToUser(dto: DeleteHomeToUserDto): Completable

}
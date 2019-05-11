package daniel.rivero.homematters.domain.interactor

import io.reactivex.Observable

interface UseCase<Type, in Params> {

    operator fun invoke(params: Params): Observable<Type>

    class None

}
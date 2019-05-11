package daniel.rivero.homematters.data.repository.utils

import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result

interface ApiResponseHandler {
    fun <R> processResponse(observableResult: Single<Result<R>>): Single<R>
}
package daniel.rivero.homematters.data.repository.utils

import daniel.rivero.homematters.domain.exception.RepositoryException
import io.reactivex.Single
import io.reactivex.SingleEmitter
import retrofit2.adapter.rxjava2.Result
import javax.inject.Inject

class ApiResponseHandlerImpl @Inject constructor(): ApiResponseHandler {

    override fun <R> processResponse(observableResult: Single<Result<R>>): Single<R> {
        return observableResult.flatMap { result ->
            Single.create<R> { single -> handleResponse(result, single) }
        }
    }

    private fun <R> handleResponse(result: Result<R>, single: SingleEmitter<R>) {
        if (isResultSuccessful(result)) {
            val body = result.response()?.body()
            body?.let { single.onSuccess(body) } ?: single.onError(RepositoryException("Body response is null."))
        } else {
            single.onError(result.error() ?: RepositoryException())
        }
    }

    private fun <T> isResultSuccessful(result: Result<T>) = result.response()?.isSuccessful ?: false

}
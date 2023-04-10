package com.ujizin.leafy.domain.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> {
    Result.Success(it)
}.onStart {
    emit(Result.Loading)
}.catch {
    emit(Result.Error(it))
}

/**
 * Check if there are any errors on results.
 *
 * @param result list of results
 * @param block callback that'll be called if there's an error
 * */
inline fun <T : Any> ifAnyError(
    vararg result: Result<T>,
    block: (List<Throwable>) -> Unit,
) {
    val errors = result.filterIsInstance<Result.Error>().mapNotNull { it.exception }
    if (errors.isNotEmpty()) block(errors)
}

/**
 * Check if results is successful and transform data on callback.
 *
 *  @param result first result
 *  @param result2 second result to be combined.
 *  @param transform callback with transformed data
 * */
inline fun <R1, R2> ifSuccess(
    result: Result<R1>,
    result2: Result<R2>,
    transform: (R1, R2) -> Unit,
) {
    if (result is Result.Success && result2 is Result.Success) {
        transform(result.data, result2.data)
    }
}

fun <T : Any> Flow<Result<T?>>.mapResult(): Flow<T> = mapNotNull { result ->
    when (result) {
        is Result.Error -> throw result.exception!!
        Result.Loading -> null
        is Result.Success -> result.data
    }
}

package br.com.devlucasyuji.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.asResult() = map {
    Result.success(it)
}.catch {
    Result.failure<Throwable>(it)
}
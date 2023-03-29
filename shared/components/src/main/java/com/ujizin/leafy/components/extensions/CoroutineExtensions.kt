package com.ujizin.leafy.components.extensions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.launchCatching(
    exceptionBlock: (CoroutineContext, Throwable) -> Unit,
    block: suspend CoroutineScope.() -> Unit
) = launch(CoroutineExceptionHandler(exceptionBlock), block = block)

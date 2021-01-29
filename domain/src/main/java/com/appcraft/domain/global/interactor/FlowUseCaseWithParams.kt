package com.appcraft.domain.global.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class FlowUseCaseWithParams<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Flow<R> {
        return withContext(coroutineDispatcher) {
            execute(parameters)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): Flow<R>
}

package com.appCraft.domain.global.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class FlowUseCase<R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(): Flow<R> {
        return withContext(coroutineDispatcher) {
            execute()
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): Flow<R>
}
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aiden.aiden.architecturepatternstudy.data.source

import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitLocalDataSource

class UpbitRepository private constructor(
    private val remoteDataSource: UpbitDataSource,
    private val localDataSource: UpbitLocalDataSource
) : UpbitDataSource {

    override fun getMarketList(
        onSuccess: (List<String>) -> Unit,
        onFail: (Throwable?) -> Unit
    ) {
        remoteDataSource.getMarketList(onSuccess, onFail)
    }

    override fun getTickerList(
        marketList: List<String>,
        isUsingLocalDb: Boolean,
        onSuccess: (List<TickerResponse>) -> Unit,
        onFail: (Throwable?) -> Unit
    ) {

        when (isUsingLocalDb) {

            true -> {
                localDataSource.getTickerList(
                    marketList,
                    true,
                    onSuccess,
                    onFail
                )
            }

            false -> {
                remoteDataSource.getTickerList(
                    marketList,
                    false,
                    onSuccess = {
                        localDataSource.saveTickerList(
                            it,
                            onSuccess = onSuccess,
                            onFail = onFail
                        )
                    },
                    onFail = onFail
                )
            }

        }

    }

    companion object {

        private var instance: UpbitRepository? = null

        operator fun invoke(
            upbitRemoteDataSource: UpbitDataSource,
            upbitLocalDataSource: UpbitLocalDataSource
        ) =
            instance ?: UpbitRepository(upbitRemoteDataSource, upbitLocalDataSource)
                .apply { instance = this }

    }
}
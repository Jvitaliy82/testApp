package com.appCraft.testApp.global.utils

import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter

fun createFastAdapter(vararg adapter: IAdapter<*>) = FastAdapter.with(adapter.toList())

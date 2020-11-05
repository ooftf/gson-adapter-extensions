package com.ooftf.engine.glda.engine

import com.google.gson.GsonBuilder
import com.ooftf.engine.glda.adapter.*
import com.ooftf.engine.glda.adapter.BooleanTypeAdapter

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/11/5
 */
fun GsonBuilder.deployLiveData(): GsonBuilder {
    registerTypeAdapterFactory(LiveDataTypeAdapterFactory())
    return this
}

fun GsonBuilder.deployDefaultValue(): GsonBuilder {
    registerTypeAdapterFactory(BooleanTypeAdapter.FACTORY)
    registerTypeAdapterFactory(DoubleTypeAdapter.FACTORY)
    registerTypeAdapterFactory(FloatTypeAdapter.FACTORY)
    registerTypeAdapterFactory(IntTypeAdapter.FACTORY)
    registerTypeAdapterFactory(LongTypeAdapter.FACTORY)
    registerTypeAdapterFactory(StringTypeAdapter.FACTORY)
    return this
}
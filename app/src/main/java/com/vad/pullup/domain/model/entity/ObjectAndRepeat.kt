package com.vad.pullup.domain.model.entity

data class ObjectAndRepeat<T>(
    val first: T,
    val repeat: Int
)
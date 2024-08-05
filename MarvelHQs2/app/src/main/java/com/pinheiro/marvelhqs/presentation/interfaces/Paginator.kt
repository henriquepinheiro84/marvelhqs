package com.pinheiro.marvelhqs.presentation.interfaces

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
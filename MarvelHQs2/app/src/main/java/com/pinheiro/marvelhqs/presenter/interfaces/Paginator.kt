package com.pinheiro.marvelhqs.presenter.interfaces

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
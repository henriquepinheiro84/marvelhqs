package com.pinheiro.marvelhqs.domain.extensions

fun String.httpToHttps(): String {
    return this.replace("http:", "https:")

}
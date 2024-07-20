package com.pinheiro.marvelhqs.domain

sealed class Errors(val code: Int, val message: String) {
    object UnoutorizedError : Errors(401, "Não autorizado")
    object UknownError : Errors(0, "Erro desconhecido")
    object ServerError : Errors(500, "Erro no servidor")
}
package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject

class ValidateLoginUseCase {
    val mockUsers = mutableListOf(
        MockUser("admin",
            "admin"),
        MockUser("local",
            "1234"),

    )
    operator fun invoke(userName: String?, password: String?): Boolean {
        if (userName?.isEmpty() == true) return false
        if(password?.isEmpty() == true) return false
        val user = mockUsers.find { userName == it.userName}
        return user?.password == password
    }
}

data class MockUser(
    val userName: String,
    val password: String
    )
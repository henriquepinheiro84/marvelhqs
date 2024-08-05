package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.BuildConfig
import java.security.MessageDigest

class GetServerHashUseCase {

    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(ts: String): String {

        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val codeToHash = ts + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        return md.digest(codeToHash.toByteArray()).toHexString()
    }
}
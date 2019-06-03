package de.nielsfalk.cms

import java.lang.ClassLoader.*
import java.security.KeyStore
import java.security.PrivateKey


val key: PrivateKey by lazy {
    val keystorePassword = "password".toCharArray()
    val keyPassword = "password".toCharArray()

    KeyStore.getInstance("PKCS12").apply {
        load(getSystemResourceAsStream("cms.p12"), keystorePassword)
    }.getKey("baeldung", keyPassword)
            as PrivateKey
}


package de.nielsfalk.cms

import org.junit.jupiter.api.Test
import org.amshove.kluent.shouldEqualTo
import javax.crypto.Cipher.getMaxAllowedKeyLength

class BouncyCastleSetup {

    @Test
    fun `should have max allowed key length`() {
        getMaxAllowedKeyLength("AES") shouldEqualTo 2147483647
    }
}

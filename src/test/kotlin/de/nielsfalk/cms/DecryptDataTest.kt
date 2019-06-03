package de.nielsfalk.cms

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class DecryptDataTest{
    @Test
    internal fun `should decrypt`() {
        val payload = "Hello World".toByteArray()
        val encryptedData = payload.encrypt()

        val decryptData = encryptedData.decrypt()

        decryptData shouldEqual payload
    }
}

package de.nielsfalk.cms

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class DecryptSignedDataTest {
    @Test
    fun `should verify and decrypt`() {
        val raw = "hello world"
        val payload = raw.toByteArray()

        val signData = payload.encrypt().sign()

        signData.decryptVerifiedData() shouldEqual payload
    }
}

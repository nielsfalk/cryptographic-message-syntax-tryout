package de.nielsfalk.cms

import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class SignDataTest {
    @Test
    fun `should verify data`() {
        val payload = "hello".toByteArray()

        val signedData = payload.sign()

        signedData.verify() shouldBe true
    }
}

package de.nielsfalk.cms

import org.bouncycastle.cms.CMSSignedData
import java.security.PrivateKey

fun ByteArray.decryptVerifiedData(decryptionKey: PrivateKey = key): ByteArray =
    verifiedData().decrypt(decryptionKey)

private fun ByteArray.verifiedData() = signedData().verifiedData()

private fun CMSSignedData.verifiedData() =
    if (verify()) {
        signedContent.content as ByteArray
    } else {
        throw RuntimeException("not verified")
    }

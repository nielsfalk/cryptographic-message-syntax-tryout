package de.nielsfalk.cms

import org.bouncycastle.cms.CMSAlgorithm
import org.bouncycastle.cms.CMSEnvelopedDataGenerator
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator
import java.security.cert.X509Certificate


fun ByteArray.encrypt(encryptionCertificate: X509Certificate = certificate): ByteArray =
    CMSEnvelopedDataGenerator().apply {
        val jceKey = JceKeyTransRecipientInfoGenerator(encryptionCertificate)
        addRecipientInfoGenerator(jceKey)
    }.generate(
        CMSProcessableByteArray(this),
        encryptor()
    ).encoded

private fun encryptor() = JceCMSContentEncryptorBuilder(CMSAlgorithm.AES128_CBC).setProvider("BC").build()


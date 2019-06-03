package de.nielsfalk.cms

import org.bouncycastle.cert.jcajce.JcaCertStore
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.CMSSignedDataGenerator
import org.bouncycastle.cms.SignerInfoGenerator
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder
import org.bouncycastle.operator.DigestCalculatorProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder
import java.security.PrivateKey
import java.security.cert.X509Certificate

fun ByteArray.sign(signingCertificate: X509Certificate = certificate, signingKey: PrivateKey = key): ByteArray =
    CMSSignedDataGenerator().apply {
        addSignerInfoGenerator(
            signerInfoGenerator(signingKey, signingCertificate)
        )
        addCertificates(JcaCertStore(listOf(signingCertificate)))
    }.generate(CMSProcessableByteArray(this), true)
        .encoded

private fun signerInfoGenerator(signingKey: PrivateKey, signingCertificate: X509Certificate): SignerInfoGenerator =
    JcaSignerInfoGeneratorBuilder(digestCalculatorProvider()).build(
        JcaContentSignerBuilder("SHA256withRSA").build(signingKey),
        signingCertificate
    )

private fun digestCalculatorProvider(): DigestCalculatorProvider =
    JcaDigestCalculatorProviderBuilder()
        .setProvider("BC")
        .build()

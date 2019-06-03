package de.nielsfalk.cms

import org.bouncycastle.asn1.ASN1InputStream
import org.bouncycastle.asn1.cms.ContentInfo
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cms.CMSSignedData
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder
import org.bouncycastle.util.Selector
import java.security.cert.X509Certificate

fun ByteArray.verify(): Boolean = signedData().verify()

fun ByteArray.signedData(): CMSSignedData =
    ASN1InputStream(this).use {
        CMSSignedData(ContentInfo.getInstance(it.readObject()))
    }

//todo: niels 03.06.2019: certificate needs to be checked
fun CMSSignedData.verify(signingCertificate: X509Certificate = certificate) =
    signerInfos.signers.first().run {
        val signerId = sid as Selector<X509CertificateHolder>
        val certificateHolder = certificates.getMatches(signerId).first()
        verify(
            JcaSimpleSignerInfoVerifierBuilder()
                .build(certificateHolder)
        )
    }




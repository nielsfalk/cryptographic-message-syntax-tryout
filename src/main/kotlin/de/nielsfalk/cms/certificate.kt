package de.nielsfalk.cms

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

val certificate: X509Certificate by lazy {
    Security.setProperty("crypto.policy", "unlimited");
    Security.addProvider(BouncyCastleProvider())

    CertificateFactory
        .getInstance("X.509", "BC")
        .generateCertificate(ClassLoader.getSystemResourceAsStream("cms.cer"))
            as X509Certificate
}

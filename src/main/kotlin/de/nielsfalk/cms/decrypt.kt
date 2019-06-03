package de.nielsfalk.cms

import org.bouncycastle.cms.CMSEnvelopedData
import org.bouncycastle.cms.KeyTransRecipientInformation
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient
import java.security.PrivateKey

fun ByteArray.decrypt(decryptionKey: PrivateKey = key): ByteArray =
    recipientInfo()
        .getContent(JceKeyTransEnvelopedRecipient(decryptionKey))

private fun ByteArray.recipientInfo(): KeyTransRecipientInformation =
    CMSEnvelopedData(this)
        .recipientInfos
        .recipients
        .first()
            as KeyTransRecipientInformation

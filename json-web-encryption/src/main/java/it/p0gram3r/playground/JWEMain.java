package it.p0gram3r.playground;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.PasswordLookup;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.security.KeyStore;

@Slf4j
public class JWEMain {
    @SneakyThrows
    public static void main(String[] args) {
        String message = "Hello world";
        log.info("original message: {}", message);

        String serializedJWE = encryptMessage(message);
        log.info("JWE Token: {}", serializedJWE);

        // ...
        // passing token to other service
        // ...

        String decryptedMessage = decryptMessage(serializedJWE);
        log.info("decrypted message: {}", decryptedMessage);

    }

    @SneakyThrows
    private static String encryptMessage(String message) {
        JWEObject jweObject = new JWEObject(
                new JWEHeader(
                        JWEAlgorithm.RSA_OAEP_256,
                        EncryptionMethod.A256GCM
                ),
                new Payload(message)
        );

        RSAKey serverKey = loadRSAKey();
        jweObject.encrypt(new RSAEncrypter(serverKey.toRSAPublicKey()));

        return jweObject.serialize();
    }

    @SneakyThrows
    private static String decryptMessage(String serializedJWEToken) {
        JWEObject jweObject = JWEObject.parse(serializedJWEToken);

        // TODO: check header, e.g. if algorithm is expected

        if (jweObject.getState() == JWEObject.State.ENCRYPTED) {
            RSAKey serverKey = loadRSAKey();
            jweObject.decrypt(new RSADecrypter(serverKey.toRSAPrivateKey()));
        }

        return jweObject.getPayload().toString();
    }

    @SneakyThrows
    private static RSAKey loadRSAKey() {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(trustStoreAsStream("server_keystore.p12"), "123456".toCharArray());

        // password to access key pair within keystore
        PasswordLookup pwLookup = name -> "123456".toCharArray();

        JWKSet jwkServerSet = JWKSet.load(keyStore, pwLookup);
        JWK serverKey = jwkServerSet.getKeyByKeyId("server");

        return serverKey.toRSAKey();
    }

    private static InputStream trustStoreAsStream(String keyStoreName) {
        return JWEMain.class.getClassLoader().getResourceAsStream(keyStoreName);
    }
}

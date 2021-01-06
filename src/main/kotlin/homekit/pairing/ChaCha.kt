package homekit.pairing

import asString
import java.nio.ByteBuffer
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * Created by Mihael Valentin Berčič
 * on 04/01/2021 at 15:43
 * using IntelliJ IDEA
 */
class ChaCha {

    // Throws Tag Mismatch...
    fun decrypt(data: ByteArray, key: ByteArray) {
        val cipher = Cipher.getInstance("ChaCha20-Poly1305")
        val buffer = ByteBuffer.allocate(12)
        buffer.position(4)
        buffer.put("PS-Msg05".toByteArray())

        val iv = IvParameterSpec(buffer.array().apply { println("Nonce: $size") })
        val spec = SecretKeySpec(key, "ChaCha20-Poly1305")
        cipher.init(Cipher.DECRYPT_MODE, spec, iv)

        val decrypted = cipher.doFinal(data.drop(12).toByteArray())
        println(decrypted.asString)
    }

    /* TODO taken online to study the implementation
    // if no nonce, generate a random 12 bytes nonce
    @JvmOverloads
    @Throws(Exception::class)
    fun encrypt(pText: ByteArray?, key: SecretKey?, nonce: ByteArray? = ChaCha.nonce): ByteArray {
        val cipher = Cipher.getInstance(ENCRYPT_ALGO)

        // IV, initialization value with nonce
        val iv = IvParameterSpec(nonce)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val encryptedText = cipher.doFinal(pText)

        // append nonce to the encrypted text
        return ByteBuffer.allocate(encryptedText.size + NONCE_LEN)
            .put(encryptedText)
            .put(nonce)
            .array()
    }

    @Throws(Exception::class)
    fun decrypt(cText: ByteArray, key: SecretKey?): ByteArray {
        val bb: ByteBuffer = ByteBuffer.wrap(cText)

        // split cText to get the appended nonce
        val encryptedText = ByteArray(cText.size - NONCE_LEN)
        val nonce = ByteArray(NONCE_LEN)
        bb.get(encryptedText)
        bb.get(nonce)
        val cipher = Cipher.getInstance(ENCRYPT_ALGO)
        val iv = IvParameterSpec(nonce)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)

        // decrypted text
        return cipher.doFinal(encryptedText)
    }

    companion object {
        private const val ENCRYPT_ALGO = "ChaCha20-Poly1305"
        private const val NONCE_LEN = 12 // 96 bits, 12 bytes

        // 96-bit nonce (12 bytes)
        private val nonce: ByteArray
            get() {
                val newNonce = ByteArray(12)
                SecureRandom().nextBytes(newNonce)
                return newNonce
            }
    }

     */
}
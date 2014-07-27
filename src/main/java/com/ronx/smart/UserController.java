package com.ronx.smart;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    @RequestMapping(value = "/key", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String key(@RequestBody String id) throws Exception {
        Preconditions.checkNotNull(id);

        Key key = generateKey();

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(id.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    @RequestMapping(value = "/value", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String value(@RequestBody String encryptedData) throws Exception {
        Key key = generateKey();
        Preconditions.checkNotNull(key);
        Cipher cipher = Cipher.getInstance(ALGO);
        byte[] ivByte = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
        cipher.init(DECRYPT_MODE, key, ivParamsSpec);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = cipher.doFinal(decordedValue);
        return new String(decValue);
    }


    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}

package chat.signa.net.pingfang.logging.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/2/17.
 */
public class Encrypt {
    public static String passKey(String info) {
        if (!info.equals("")) {
            byte[] key="!^@#$*&(".getBytes();
            byte[] IV="$^)7$&~@".getBytes();
            byte[] bytes=info.trim().getBytes();

            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bytes);
                //CryptoStream CS = new CryptoStream(MS, tDES.CreateEncryptor(key, IV), CryptoStreamMode.Write)

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
            return info;

    }
}

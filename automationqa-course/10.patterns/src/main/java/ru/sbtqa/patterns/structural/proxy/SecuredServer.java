package ru.sbtqa.patterns.structural.proxy;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.WebServiceException;
import java.security.Key;
import java.util.Arrays;

public class SecuredServer implements Server {

    private RealServer server;

    public SecuredServer(String url) {
        this.server = new RealServer(url);
    }

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    @Override
    public String sendMessage(String message) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            System.out.println("Encrypted message - " + Arrays.toString(encrypted));
            return server.sendMessage(new String(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebServiceException();
        }
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }
}

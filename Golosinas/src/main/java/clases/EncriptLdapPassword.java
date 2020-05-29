/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author mmarvin
 */
public class EncriptLdapPassword {

    private final static boolean debug = false;

    public EncriptLdapPassword() {

    }

    private String encryptLdapPassword(String algorithm, String _password) {
        String sEncrypted = _password;
        if ((_password != null) && (_password.length() > 0)) {
            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
                    || algorithm.equalsIgnoreCase("SHA1")
                    || algorithm.equalsIgnoreCase("SHA-1");
            if (bSHA || bMD5) {
                String sAlgorithm = "MD5";
                if (bSHA) {
                    sAlgorithm = "SHA";
                }
                try {
                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
                    md.update(_password.getBytes("UTF-8"));
                    //sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
                    sEncrypted = "{" + sAlgorithm + "}" + Base64.getEncoder().encodeToString(md.digest());
                    System.out.println(sEncrypted);
                } catch (Exception e) {
                    sEncrypted = null;
                    // logger.error(e, e);
                }
            }
        }
        return sEncrypted;
    }

    static private Charset UTF8() {
        return Charset.forName("UTF-8");
    }

    static private MessageDigest getSHA1() {
        try {
            return MessageDigest.getInstance("SHA-1");
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    static public String getSSHAHash(String plaintext, String salt) {
        byte saltBytes[];
        MessageDigest hasher = getSHA1();

        /* -- */
        if (salt == null) {
            saltBytes = genRandomSalt();
        } else {
            saltBytes = salt.getBytes(UTF8());
        }

        hasher.reset();
        hasher.update(plaintext.getBytes(UTF8()));
        hasher.update(saltBytes);
        byte digestBytes[] = hasher.digest();
        byte outBytes[] = new byte[saltBytes.length + 20]; // SHA1 hash is 20 bytes long

        assert (digestBytes.length == 20);

        System.arraycopy(digestBytes, 0, outBytes, 0, digestBytes.length);
        System.arraycopy(saltBytes, 0, outBytes, digestBytes.length, saltBytes.length);

        return com.lowagie.text.pdf.codec.Base64.encodeBytes(outBytes);
    }

    static public String getLDAPSSHAHash(String plaintext, String salt) {
        return "{SSHA}" + getSSHAHash(plaintext, salt);
    }

    static public boolean matchSHAHash(String hashText, String plaintext) {
        byte[] hashBytes;
        byte[] plainBytes;
        byte[] saltBytes = null;
        MessageDigest hasher = getSHA1();

        /* -- */
        if (hashText.indexOf("{SSHA}") != -1) {
            hashText = hashText.substring(6);
        } else if (hashText.indexOf("{SHA}") != -1) {
            hashText = hashText.substring(5);
        }

        if (debug) {
            System.err.println("Wha!  " + hashText);
        }

        hashBytes = com.lowagie.text.pdf.codec.Base64.decode(hashText);

        if (hashBytes.length > 20) {
            saltBytes = new byte[hashBytes.length - 20];

            for (int i = 20; i < hashBytes.length; i++) {
                saltBytes[i - 20] = hashBytes[i];
            }

            if (debug) {
                System.err.println("Salt is " + new String(saltBytes));
            }
        }

        if (saltBytes != null) {
            byte[] inBytes = plaintext.getBytes(UTF8());
            plainBytes = new byte[inBytes.length + saltBytes.length];

            for (int i = 0; i < inBytes.length; i++) {
                plainBytes[i] = inBytes[i];
            }

            for (int i = 0; i < saltBytes.length; i++) {
                plainBytes[i + inBytes.length] = saltBytes[i];
            }
        } else {
            plainBytes = plaintext.getBytes(UTF8());
        }

        if (debug) {
            System.err.println("Match text is " + new String(plainBytes));
        }

        // okay, now we should have in plainBytes the input to the SHA
        // algorithm that would have been used to generate the hashText if
        // we indeed have a match.  Let's just check.
        hasher.reset();
        hasher.update(plainBytes);
        byte matchBytes[] = hasher.digest();

        assert (matchBytes.length == 20);

        for (int i = 0; i < matchBytes.length; i++) {
            if (matchBytes[i] != hashBytes[i]) {
                if (debug) {
                    System.err.println("Char mismatch [" + i + "]");
                }

                return false;
            }
        }

        return true;
    }

    static public byte[] genRandomSalt() {

        java.util.Random randgen = new java.util.Random();
        byte[] saltBytes = new byte[4];

        /* -- */
        randgen.nextBytes(saltBytes);

        return saltBytes;
    }

}

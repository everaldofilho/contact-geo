package com.contact.geo.contactgeo.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class GravatarService {
    private final String URL_BASE = "https://www.gravatar.com/avatar/";

    public String getImageURL(String email) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email em branco");
        }

        String emailClear = cleanEmail(email);
        String emailEncode = encodeMD5(emailClear);
        return URL_BASE.concat(emailEncode).concat("?d=monsterid");
    }

    private String cleanEmail(String email) {
        return email.toLowerCase().trim();
    }

    private String encodeMD5(String email) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(email.getBytes());

        byte[] digest = md5.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++)
            hexString.append(Integer.toHexString(0xFF & digest[i]));

        return hexString.toString();
    }
}

package com.comuctiva.comuctiva.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPasswords {
    public static void main(String[] args){
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

        String[] passwords = {"camila123", "maria123", "mateo123", "alexa123", "jhorman123", "carlos123"};
        String[] numDocs = {"55555555", "66666666", "77777777", "11111111", "33333333", "44444444"};

        for (int i = 0; i < passwords.length; i++) {
            String hash = enc.encode(passwords[i]);
            System.out.println("UPDATE usuario SET password = '" + hash + "' WHERE NumDoc = '" + numDocs[i] + "';");
        }
    } 
}

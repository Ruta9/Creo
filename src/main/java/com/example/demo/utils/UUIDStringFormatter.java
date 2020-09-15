package com.example.demo.utils;

import java.util.UUID;

public class UUIDStringFormatter {

    public static UUID convertToUUID (String uuid){
        System.out.println(uuid);
        String uuidFormatted = uuid.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5");
        System.out.println(uuidFormatted);
        return UUID.fromString(uuidFormatted);
    }
}

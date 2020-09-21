package com.example.demo.enums;

import org.springframework.http.MediaType;

import java.util.AbstractMap;
import java.util.Map;

public  class MediaTypes {

    public static Map<String, MediaType> mediaTypesByExtension = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, MediaType>(".png", MediaType.IMAGE_PNG),
            new AbstractMap.SimpleEntry<String, MediaType>(".jpeg", MediaType.IMAGE_JPEG)
    );
}

package com.example.demo.utils;

import com.example.demo.enums.MediaTypes;
import org.springframework.http.MediaType;

public class FileExtension {

    public static MediaType getMediaType(String fileName){
        return MediaTypes.mediaTypesByExtension.get(getExtension(fileName));
    }

    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}

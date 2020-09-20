package com.example.demo.DTOs;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
public class ProjectGeneralInformationForm {

    ProjectGeneralInformation projectInformation;
    MultipartFile projectImage;
}

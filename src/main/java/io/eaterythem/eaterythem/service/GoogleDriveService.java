package io.eaterythem.eaterythem.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import io.eaterythem.eaterythem.tools.GoogleDriveUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class GoogleDriveService {
    
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final Drive driveService;

    public GoogleDriveService() throws Exception {
        InputStream in = new FileInputStream("src/main/resources/credentials.json");

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        this.driveService = new Drive.Builder(
                httpTransport,
                JSON_FACTORY,
                GoogleDriveUtils.getCredentials(in))
                .setApplicationName("EateryThem")
                .build();
    }

    public String uploadFile(MultipartFile multipartFile) throws Exception {
        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());

        InputStreamContent mediaContent =
                new InputStreamContent(multipartFile.getContentType(), multipartFile.getInputStream());

        File uploadedFile = driveService.files()
                .create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        // make it public
        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        driveService.permissions().create(uploadedFile.getId(), permission).execute();

        return "https://drive.google.com/uc?id=" + uploadedFile.getId();
    }
}

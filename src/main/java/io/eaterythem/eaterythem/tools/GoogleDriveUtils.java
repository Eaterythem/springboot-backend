package io.eaterythem.eaterythem.tools;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.InputStream;
import java.util.Collections;

public class GoogleDriveUtils {

    public static HttpCredentialsAdapter getCredentials(InputStream in) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive.file"));

        return new HttpCredentialsAdapter(credentials);
    }
}

package au.com.rmit.misionMujer.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class ProjectService {


//    https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-install.html
    static final Logger LOG = LoggerFactory.getLogger(ProjectService.class);
    private static ProjectService instance;
    private ProjectService() { }

    public static  ProjectService getInstance() {
        if(instance == null) {
            instance = new ProjectService();
        }

        return instance;
    }

    public void createProject() throws IOException, GeneralSecurityException {

    }



}

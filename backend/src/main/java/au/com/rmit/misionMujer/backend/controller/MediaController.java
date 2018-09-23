package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.model.Media;
import au.com.rmit.misionMujer.backend.model.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/media")
public class MediaController {

    static final Logger LOG = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaRepository mediaRepository;

    @RequestMapping(path="/")
    public @ResponseBody Iterable<Media> getAllMedia() {
        // This returns a JSON or XML with the users
        return mediaRepository.findAll();
    }

}

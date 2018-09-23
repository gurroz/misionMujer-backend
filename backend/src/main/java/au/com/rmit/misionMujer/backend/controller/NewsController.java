package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.model.News;
import au.com.rmit.misionMujer.backend.model.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/news")
public class NewsController {

    static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(path="/")
    public @ResponseBody Iterable<News> getAllNews() {
        // This returns a JSON or XML with the users
        return newsRepository.findAll();
    }

}

package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.dto.NewsDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.News;
import au.com.rmit.misionMujer.backend.services.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/news")
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {

    static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService = null;

    @RequestMapping(path="/")
    public @ResponseBody
    List<News> getAllNews() {
        return newsService.getNews();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNews(@RequestBody NewsDTO newsDTO) throws ElementAlreadyExistsException {
        newsService.createNews(newsDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable("id") int newsId) {
        newsService.deleteNews(newsId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createNews(@PathVariable("id") int newsId, @RequestBody NewsDTO newsDTO) throws ElementNotExistsException {
        newsService.editNews(newsId, newsDTO);
    }

}

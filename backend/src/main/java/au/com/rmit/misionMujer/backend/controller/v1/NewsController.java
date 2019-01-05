package au.com.rmit.misionMujer.backend.controller.v1;

import au.com.rmit.misionMujer.backend.dto.NewsDTO;
import au.com.rmit.misionMujer.backend.dto.ResponseDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.exceptions.InternalServerException;
import au.com.rmit.misionMujer.backend.model.News;
import au.com.rmit.misionMujer.backend.services.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/news")
public class NewsController {

    static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @RequestMapping(path="/")
    public @ResponseBody ResponseDTO getAllNews() throws InternalServerException  {
        try {
            ResponseDTO<List<News>> response = new ResponseDTO();
            response.setData(newsService.getNews());
            response.setResult("OK");

            return response;
        } catch (Exception e) {
            LOG.error("Error getAllNews", e);
            throw new InternalServerException();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNews(@RequestBody NewsDTO newsDTO) throws ElementAlreadyExistsException {
        newsService.createNews(newsDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable("id") int newsId) throws InternalServerException {
        try {
            newsService.deleteNews(newsId);
        } catch (Exception e) {
            LOG.error("Error getAllNews", e);
            throw new InternalServerException();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNews(@PathVariable("id") int newsId, @RequestBody NewsDTO newsDTO) throws ElementNotExistsException {
        newsService.editNews(newsId, newsDTO);
    }

}

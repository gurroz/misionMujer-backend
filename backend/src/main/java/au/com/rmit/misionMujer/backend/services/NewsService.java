package au.com.rmit.misionMujer.backend.services;

import au.com.rmit.misionMujer.backend.dto.NewsDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.News;
import au.com.rmit.misionMujer.backend.model.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
@Scope(value = "singleton")
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    static final Logger LOG = LoggerFactory.getLogger(NewsService.class);
    private static NewsService instance;
    private NewsService() { }

    public static NewsService getInstance() {
        if(instance == null) {
            instance = new NewsService();
        }

        return instance;
    }

    public List<News> getNews() {
        List<News> list = new ArrayList<>();
        Iterable<News> newsIterator = newsRepository.findAll();
        if(newsIterator != null) {
            newsIterator.iterator().forEachRemaining(list::add);
        }
        return list;
    }

    public void createNews(NewsDTO newsDTO) throws ElementAlreadyExistsException {
        LOG.debug("Creating new News", newsDTO);
        News newNews = getFrom(newsDTO,null);
        LOG.debug("News does not exist", newNews);
        newsRepository.save(newNews);
    }

    public void deleteNews(Integer newsId) {
        newsRepository.deleteById(newsId);
    }

    public void editNews(Integer categoryId, NewsDTO newsDTO) throws ElementNotExistsException {
        LOG.debug("Updating news", newsDTO);
        News news = newsRepository.findById(categoryId).orElseThrow(() -> new ElementNotExistsException());
        News newNews = getFrom(newsDTO,news);

        newsRepository.save(newNews);
    }

    private News getFrom(NewsDTO newsDTO, News original) {
        News news = original;
        if(original == null) {
            news = new News();
        }
        news.setContent(newsDTO.getContent());
        news.setDescription(newsDTO.getDescription());
        news.setTitle(newsDTO.getTitle());
        news.setImage(newsDTO.getImage());
        news.setPublished(new Date());

        return news;
    }

}

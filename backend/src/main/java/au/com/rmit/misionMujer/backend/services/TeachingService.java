package au.com.rmit.misionMujer.backend.services;

import au.com.rmit.misionMujer.backend.dto.CategoryDTO;
import au.com.rmit.misionMujer.backend.dto.TeachingDTO;
import au.com.rmit.misionMujer.backend.enums.MediaType;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.Category;
import au.com.rmit.misionMujer.backend.model.Teaching;
import au.com.rmit.misionMujer.backend.model.TeachingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeachingService {

    static final Logger LOG = LoggerFactory.getLogger(TeachingService.class);

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private CategoryService categoryService;

    public TeachingService() { }

    @Cacheable("teachings")
    public List<Teaching> getTeaching() {
        List<Teaching> list = new ArrayList<>();
        Iterable<Teaching> newsIterator = teachingRepository.findAll();
        if(newsIterator != null) {
            newsIterator.iterator().forEachRemaining(list::add);
        }
        return list;
    }


    @CacheEvict(value="teachings",  allEntries=true)
    public Teaching createTeaching(TeachingDTO newsDTO) throws ElementAlreadyExistsException {
        LOG.debug("Creating new Teaching", newsDTO);
        Teaching newTeaching = getFrom(newsDTO,null);
        LOG.debug("Teaching does not exist", newTeaching);
        return teachingRepository.save(newTeaching);
    }

    @CacheEvict(value="teachings",  allEntries=true)
    public void deleteTeaching(Integer newsId) {
        teachingRepository.deleteById(newsId);
    }

    @CacheEvict(value="teachings",  allEntries=true)
    public Teaching editTeaching(Integer teachingId, TeachingDTO teachingDTO) throws ElementNotExistsException {
        LOG.debug("Updating teaching", teachingDTO);
        Teaching teaching = teachingRepository.findById(teachingId).orElseThrow(() -> new ElementNotExistsException());
        Teaching newTeaching = getFrom(teachingDTO, teaching);

        return teachingRepository.save(newTeaching);
    }

    private Teaching getFrom(TeachingDTO teachingDTO, Teaching original) {
        LOG.debug("Creating teaching", teachingDTO);

        Teaching teaching = original;
        if(original == null) {
            teaching = new Teaching();
        }

        if(teachingDTO.getImage() != null) {
            teaching.setCover(teachingDTO.getImage());
        }

        if(teachingDTO.getFile() != null) {
            teaching.setFile(teachingDTO.getFile());
        }

        teaching.setLength((int) (Math.random() * 10000));
        teaching.setDescription(teachingDTO.getDescription());
        teaching.setTitle(teachingDTO.getTitle());
        teaching.setType(MediaType.valueOf(teachingDTO.getType()));
        teaching.setContent(teachingDTO.getContent());
        teaching.setPublished(new Date());

        List<Category> categories = new ArrayList<Category>();
        for(CategoryDTO categoryDTO : teachingDTO.getCategories()) {
            Category category = categoryService.getCategoryByName(categoryDTO.getName());
            if(category != null) {
                categories.add(category);
            }
        }

        teaching.setCategories(categories);
        return teaching;
    }

}

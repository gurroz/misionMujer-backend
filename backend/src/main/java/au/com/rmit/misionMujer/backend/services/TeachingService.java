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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
@Scope(value = "singleton")
public class TeachingService {

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private CategoryService categoryService = null;

    static final Logger LOG = LoggerFactory.getLogger(TeachingService.class);
    private static TeachingService instance;
    private TeachingService() { }

    public static TeachingService getInstance() {
        if(instance == null) {
            instance = new TeachingService();
        }

        return instance;
    }

    public List<Teaching> getTeaching() {
        List<Teaching> list = new ArrayList<>();
        Iterable<Teaching> newsIterator = teachingRepository.findAll();
        if(newsIterator != null) {
            newsIterator.iterator().forEachRemaining(list::add);
        }
        return list;
    }

    public void createTeaching(TeachingDTO newsDTO) throws ElementAlreadyExistsException {
        LOG.debug("Creating new Teaching", newsDTO);
        Teaching newTeaching = getFrom(newsDTO,null);
        LOG.debug("Teaching does not exist", newTeaching);
        teachingRepository.save(newTeaching);
    }

    public void deleteTeaching(Integer newsId) {
        teachingRepository.deleteById(newsId);
    }

    public void editTeaching(Integer teachingId, TeachingDTO teachingDTO) throws ElementNotExistsException {
        LOG.debug("Updating teaching", teachingDTO);
        Teaching teaching = teachingRepository.findById(teachingId).orElseThrow(() -> new ElementNotExistsException());
        Teaching newTeaching = getFrom(teachingDTO, teaching);

        teachingRepository.save(newTeaching);
    }

    private Teaching getFrom(TeachingDTO teachingDTO, Teaching original) {
        LOG.debug("Creating teaching", teachingDTO);

        Teaching teaching = original;
        if(original == null) {
            teaching = new Teaching();
        }
        teaching.setCover(teachingDTO.getImage());
        teaching.setDescription(teachingDTO.getDescription());
        teaching.setTitle(teachingDTO.getTitle());
        teaching.setFile(teachingDTO.getFile());
        teaching.setType(MediaType.valueOf(teachingDTO.getType()));
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

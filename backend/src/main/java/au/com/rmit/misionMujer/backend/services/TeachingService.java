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

    public List<Teaching> getTeaching() {
        List<Teaching> list = new ArrayList<>();
        Iterable<Teaching> newsIterator = teachingRepository.findAll();
        if(newsIterator != null) {
            newsIterator.iterator().forEachRemaining(list::add);
        }
        return list;
    }

    public Teaching createTeaching(TeachingDTO newsDTO) throws ElementAlreadyExistsException {
        LOG.debug("Creating new Teaching", newsDTO);
        Teaching newTeaching = getFrom(newsDTO,null);
        LOG.debug("Teaching does not exist", newTeaching);
        return teachingRepository.save(newTeaching);
    }

    public void deleteTeaching(Integer newsId) {
        teachingRepository.deleteById(newsId);
    }

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
        teaching.setCover("");
        teaching.setLength(0);
        teaching.setDescription(teachingDTO.getDescription());
        teaching.setTitle(teachingDTO.getTitle());
        teaching.setFile(teachingDTO.getFile());
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

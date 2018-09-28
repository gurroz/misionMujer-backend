package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.dto.TeachingDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.Teaching;
import au.com.rmit.misionMujer.backend.services.TeachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/teachings")
public class TeachingController {

    static final Logger LOG = LoggerFactory.getLogger(TeachingController.class);

    @Autowired
    private TeachingService teachingService;

    @RequestMapping(path="/")
    @Cacheable("teachings")
    public @ResponseBody List<Teaching> getAllTeaching() {
        return teachingService.getTeaching();
    }

    @PostMapping("/")
    @CachePut("teachings")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeaching(@RequestBody TeachingDTO teachingDTO) throws ElementAlreadyExistsException {
        teachingService.createTeaching(teachingDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value="teachings",  allEntries=true)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTeaching(@PathVariable("id") int teachingId) {
        teachingService.deleteTeaching(teachingId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeaching(@PathVariable("id") int teachingId, @RequestBody TeachingDTO teachingDTO) throws ElementNotExistsException {
        teachingService.editTeaching(teachingId, teachingDTO);
    }


}

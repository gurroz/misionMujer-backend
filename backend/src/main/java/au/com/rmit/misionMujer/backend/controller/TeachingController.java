package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.dto.TeachingDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.Teaching;
import au.com.rmit.misionMujer.backend.services.TeachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/teachings")
@CrossOrigin(origins = "http://localhost:4200")
public class TeachingController {

    static final Logger LOG = LoggerFactory.getLogger(TeachingController.class);

    @Autowired
    private TeachingService teachingService = null;

    @RequestMapping(path="/")
    public @ResponseBody
    List<Teaching> getAllTeaching() {
        return teachingService.getTeaching();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeaching(@RequestBody TeachingDTO categoryDTO) throws ElementAlreadyExistsException {
        teachingService.createTeaching(categoryDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTeaching(@PathVariable("id") int categoryId) {
        teachingService.deleteTeaching(categoryId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createTeaching(@PathVariable("id") int categoryId, @RequestBody TeachingDTO teachingDTO) throws ElementNotExistsException {
        teachingService.editTeaching(categoryId, teachingDTO);
    }


}
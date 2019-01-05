package au.com.rmit.misionMujer.backend.controller.v1;

import au.com.rmit.misionMujer.backend.dto.ResponseDTO;
import au.com.rmit.misionMujer.backend.dto.TeachingDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.exceptions.InternalServerException;
import au.com.rmit.misionMujer.backend.model.News;
import au.com.rmit.misionMujer.backend.model.Teaching;
import au.com.rmit.misionMujer.backend.services.TeachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/teachings")
public class TeachingController {

    static final Logger LOG = LoggerFactory.getLogger(TeachingController.class);

    @Autowired
    private TeachingService teachingService;

    @RequestMapping(path="/")
    public @ResponseBody ResponseDTO getAllTeaching() throws InternalServerException {
        try {
            ResponseDTO<List<Teaching>> response = new ResponseDTO();
            response.setData(teachingService.getTeaching());
            response.setResult("OK");

            return response;
        } catch (Exception e) {
            LOG.error("Error getAllTeaching", e);
            throw new InternalServerException();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeaching(@RequestBody TeachingDTO teachingDTO) throws ElementAlreadyExistsException {
        teachingService.createTeaching(teachingDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTeaching(@PathVariable("id") int teachingId) throws InternalServerException{
        try {
            teachingService.deleteTeaching(teachingId);
        } catch (Exception e) {
            LOG.error("Error deleteTeaching", e);
            throw new InternalServerException();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeaching(@PathVariable("id") int teachingId, @RequestBody TeachingDTO teachingDTO) throws ElementNotExistsException {
        teachingService.editTeaching(teachingId, teachingDTO);
    }


}

package au.com.rmit.misionMujer.backend.controller.v1;

import au.com.rmit.misionMujer.backend.dto.CategoryDTO;
import au.com.rmit.misionMujer.backend.dto.ResponseDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.exceptions.InternalServerException;
import au.com.rmit.misionMujer.backend.model.Category;
import au.com.rmit.misionMujer.backend.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/categories")
public class CategoryController {

    static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/")
    public @ResponseBody ResponseDTO getAllCategories() throws InternalServerException {
        try {
            ResponseDTO<List<Category>> response = new ResponseDTO();
            response.setData(categoryService.getCategories());
            response.setResult("OK");

            return response;
        } catch (Exception e) {
            LOG.error("Error getCategories", e);
            throw new InternalServerException();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDTO categoryDTO) throws ElementAlreadyExistsException {
        categoryService.createCategory(categoryDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") int categoryId) throws InternalServerException {
        try {
            categoryService.deleteCategory(categoryId);
        } catch (Exception e) {
            LOG.error("Error deleteCategory", e);
            throw new InternalServerException();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@PathVariable("id") int categoryId, @RequestBody CategoryDTO categoryDTO) throws ElementNotExistsException {
        categoryService.editCategory(categoryId, categoryDTO);
    }
}

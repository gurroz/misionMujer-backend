package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.dto.CategoryDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.Category;
import au.com.rmit.misionMujer.backend.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/categories")
public class CategoryController {

    static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService = null;

    @RequestMapping(path="/")
    public @ResponseBody List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDTO categoryDTO) throws ElementAlreadyExistsException {
        categoryService.createCategory(categoryDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") int categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createCategory(@PathVariable("id") int categoryId, @RequestBody CategoryDTO categoryDTO) throws ElementNotExistsException {
        categoryService.editCategory(categoryId, categoryDTO);
    }
}

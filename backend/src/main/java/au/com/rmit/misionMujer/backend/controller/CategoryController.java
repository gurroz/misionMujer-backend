package au.com.rmit.misionMujer.backend.controller;

import au.com.rmit.misionMujer.backend.model.Category;
import au.com.rmit.misionMujer.backend.model.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/categories")
public class CategoryController {

    static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path="/")
    public @ResponseBody Iterable<Category> getAllCategories() {
        // This returns a JSON or XML with the users
        return categoryRepository.findAll();
    }
}

package au.com.rmit.misionMujer.backend.services;

import au.com.rmit.misionMujer.backend.dto.CategoryDTO;
import au.com.rmit.misionMujer.backend.exceptions.ElementAlreadyExistsException;
import au.com.rmit.misionMujer.backend.exceptions.ElementNotExistsException;
import au.com.rmit.misionMujer.backend.model.Category;
import au.com.rmit.misionMujer.backend.model.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

//    https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-install.html
    public CategoryService() { }

    @Cacheable("categories")
    public List<Category> getCategories() {
        List<Category> list = new ArrayList<>();
        Iterable<Category> catIterator = categoryRepository.findAll();
        if(catIterator != null) {
            catIterator.iterator().forEachRemaining(list::add);
        }
        return list;
    }

    @CacheEvict(value="categories",  allEntries=true)
    public void createCategory(CategoryDTO categoryDTO) throws ElementAlreadyExistsException {
        LOG.debug("Creating new category", categoryDTO);
        if(this.getCategoryByName(categoryDTO.getName()) != null) {
            throw new ElementAlreadyExistsException();
        }

        Category newCategory = getFrom(categoryDTO);
        LOG.debug("Category does not exist", newCategory);
        categoryRepository.save(newCategory);
    }

    @CacheEvict(value="categories",  allEntries=true)
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @CacheEvict(value="categories",  allEntries=true)
    public void editCategory(Integer categoryId, CategoryDTO categoryDTO) throws ElementNotExistsException {
        LOG.debug("Updating new category", categoryDTO);
        Category category = this.getCategoryById(categoryId);

        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());

        categoryRepository.save(category);
    }

    public Category getCategoryById(Integer categoryId) throws ElementNotExistsException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ElementNotExistsException());
        return category;
    }

    public Category getCategoryByName(String name) {
        List<Category> actualCategories = this.getCategories();
        for(Category category : actualCategories) {
            if(category.getName().toLowerCase().equals(name.toLowerCase())) {
                return category;
            }
        }

        return null;
    }

    private Category getFrom(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setImage(categoryDTO.getImage());
        category.setName(categoryDTO.getName());

        return category;
    }

}

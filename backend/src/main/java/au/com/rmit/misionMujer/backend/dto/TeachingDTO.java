package au.com.rmit.misionMujer.backend.dto;

import java.util.List;
import java.util.StringJoiner;

public class TeachingDTO {
    private String title;
    private String description;
    private String content;
    private String type;
    private String file;
    private List<CategoryDTO> categories;

    public TeachingDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TeachingDTO.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("content='" + content + "'")
                .add("type='" + type + "'")
                .add("file='" + file + "'")
                .add("categories=" + categories)
                .toString();
    }
}
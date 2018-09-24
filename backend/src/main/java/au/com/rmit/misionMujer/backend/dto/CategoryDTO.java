package au.com.rmit.misionMujer.backend.dto;

import java.util.StringJoiner;

public class CategoryDTO {
    private String name;
    private String image;

    public CategoryDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CategoryDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("image='" + image + "'")
                .toString();
    }
}

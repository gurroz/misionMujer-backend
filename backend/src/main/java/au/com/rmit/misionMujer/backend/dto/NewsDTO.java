package au.com.rmit.misionMujer.backend.dto;

import java.util.StringJoiner;

public class NewsDTO {
    private String title;
    private String image;
    private String description;
    private String content;

    public NewsDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NewsDTO.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("image='" + image + "'")
                .add("description='" + description + "'")
                .add("content='" + content + "'")
                .toString();
    }
}

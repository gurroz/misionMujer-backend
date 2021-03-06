package au.com.rmit.misionMujer.backend.model;

import au.com.rmit.misionMujer.backend.enums.MediaType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Teaching {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private Date published;
    private MediaType type;
    private String cover;
    private Integer length;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String file;

    @ManyToMany(cascade=ALL)
    private List<Category> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teaching projects = (Teaching) o;

        return id != null ? id.equals(projects.id) : projects.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Teaching.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("published=" + published)
                .add("type=" + type)
                .add("cover='" + cover + "'")
                .add("length=" + length)
                .add("description='" + description + "'")
                .add("content='" + content + "'")
                .add("file='" + file + "'")
                .add("categories=" + categories)
                .toString();
    }
}

package at.technikum.wien.if20b206.if20b211.sksportzal.entity;

import javax.persistence.*;

@Entity
public class ArticlesEntity {

    public ArticlesEntity(String title, String text, String author, Long sightseeingId) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.sightseeingId = sightseeingId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "author")
    private String author;
    @Column(name = "sightseeingId", unique = true)
    private Long sightseeingId;

    public ArticlesEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getSightseeingId() {
        return sightseeingId;
    }

    public void setSightseeingId(Long sightseeingId) {
        this.sightseeingId = sightseeingId;
    }
}

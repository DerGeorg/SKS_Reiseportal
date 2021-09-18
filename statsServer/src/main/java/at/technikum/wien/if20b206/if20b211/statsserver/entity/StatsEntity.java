package at.technikum.wien.if20b206.if20b211.statsserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class StatsEntity {

    @Id
    @Column(name = "articleid")
    private int id;

    @Column(name = "count")
    private int count;

    private Date pubDate;

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void addCount() {
        this.count++;
    }

    public StatsEntity(){

    }
}

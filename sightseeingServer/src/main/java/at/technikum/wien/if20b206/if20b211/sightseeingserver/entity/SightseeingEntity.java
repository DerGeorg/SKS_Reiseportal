package at.technikum.wien.if20b206.if20b211.sightseeingserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class SightseeingEntity {

    @Id
    @Column(name = "sightseeing_id")
    private int id;

    @Column(name = "name")
    private String name;


}
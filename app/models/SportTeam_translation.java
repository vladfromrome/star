package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: mgt
 * Date: 01.04.14
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SportTeam_translation extends Model{
    @Id
    public Long id;
    public static Finder<Long, SportTeam_translation> FIND = new Finder<>(Long.class, SportTeam_translation.class);

    @ManyToOne
    public SportTeam team;
    public String lang;
    public String name;

    public SportTeam_translation(String lang, String name, SportTeam team) {
        this.lang = lang;
        this.name = name;
        this.team = team;
    }
}

package models.translatable;

import models.dbmessages.Language;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 14:25
 */
@Entity
public class Translation extends Model{
    @Id
    public Long id;

    public String label;

    @ManyToOne
    public Language language;

    public Translation(String label, Language language) {
        this.label = label;
        this.language = language;
    }

}

package models.toto;

import models.dbmessages.Language;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 10:29
 */

public class Translation {
    public Translation(String label, Language language) {
        this.label = label;
        this.language = language;
    }

    @Id
    public Integer id;

    public String label;

    @ManyToOne
    public Language language;

//    @ManyToOne(cascade = CascadeType.ALL)
//    public List<Sport> sports = new ArrayList<Sport>();

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Sport> sports = new ArrayList<Sport>();

}

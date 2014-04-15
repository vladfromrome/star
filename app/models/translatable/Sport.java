package models.translatable;

import models.dbmessages.Language;
import play.db.ebean.Model;

import javax.persistence.Entity;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 14:24
 */
@SuppressWarnings("serial")
@Entity
public class Sport extends Translatable {
    public static Model.Finder<Long,Sport> FIND = new Model.Finder<>(Long.class,Sport.class);


    public Sport(String tag, String englishLabel, String russianLabel){
        this.tag = tag;
        this.translations.add(new Translation(englishLabel, Language.getEnglish()));
        this.translations.add(new Translation(russianLabel, Language.getRussian()));
        this.save();
    }

    public static Sport getByTag(String tag){
        return FIND.where().eq("tag",tag).findUnique();
    }

    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Sport))return false;
        Sport s = (Sport) o;
        if(!s.tag.equals(this.tag))return false;
        return true;
    }
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + this.id.hashCode();
        return result;
    }
}

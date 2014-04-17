package models.translatable;

import models.dbmessages.Language;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 14:24
 */
@SuppressWarnings("serial")
@Entity
public class Sport extends Model implements TranslatableInterface{
    @Id
    @Column(name = "sportId")
    public Long id;
    public static Model.Finder<Long,Sport> FIND = new Model.Finder<>(Long.class,Sport.class);

    @Column(unique = true)
    public String tag;
    @ManyToMany(cascade = CascadeType.ALL)     //in fact it is one-to-many if it is a unidirectional relationship and the actual "@OneToMany" doesn't work here
    @JoinTable (name = "Sport_Translation")
    //@JoinColumn(name="translatable_id", referencedColumnName = "translatable_id")
    public List<Translation> translations = new ArrayList<Translation>();

    public Sport(String tag, String englishLabel, String russianLabel){
        this.tag = tag;
        this.translations.add(new Translation(englishLabel, Language.getEnglish()));
        this.translations.add(new Translation(russianLabel, Language.getRussian()));
        this.save();
    }

    public static Sport getByTag(String tag){
        return FIND.where().eq("tag",tag).findUnique();
    }

    public String getTranslationByLangCode(String langCode) {
        for(Translation st : translations){
            if(langCode.equals(st.language.code)){
                return st.label;
            }
        }
        return this.tag;
    }
    public String getTranslation(){
        try {
            return getTranslationByLangCode(Language.getCurrentLanguageCode());
        } catch (Exception e){
            return "translationExceptionMsg";
        }
    }

    public static List<String> getTranslationsList(){
        List<Sport> sports = FIND.all();
        List<String> list=new ArrayList<>();
        for (Sport sp:sports){
            list.add(sp.getTranslation());
        }
        return list;
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

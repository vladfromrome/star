package models.translatable;

import models.dbmessages.Language;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 14:25
 */
@SuppressWarnings("serial")
@Entity
public class Subject extends Model implements TranslatableInterface {
    @Id
    @Column(name = "subjId")
    public Long id;
    public static Model.Finder<Long,Subject> FIND = new Model.Finder<>(Long.class,Subject.class);

    @Column(unique = true)
    public String tag;
    @ManyToMany(cascade = CascadeType.ALL)       //in fact it is one-to-many if it is a unidirectional relationship and the actual"@OneToMany" doesn't work here
    @JoinTable (name = "Subject_Translation")
    public List<Translation> translations = new ArrayList<Translation>();

    public Subject(String tag, String englishLabel, String russianLabel){
        this.tag = tag;
        this.translations.add(new Translation(englishLabel, Language.getByCode("en")));
        this.translations.add(new Translation(russianLabel, Language.getByCode("ru")));
        this.save();
    }

    public static Subject getByTag(String tag){
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
        List<Subject> subjects = FIND.all();
        List<String> list=new ArrayList<>();
        for (Subject s:subjects){
            list.add(s.getTranslation());
        }
        return list;
    }

}

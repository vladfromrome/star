package models.toto;

import models.dbmessages.Language;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 10:29
 */
public class Subject extends Translatable {

//    @Id
//    public Long id;
//
//    public String tag;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Translation> translations = new ArrayList<Translation>();

    public  Subject(String tag){
        this.tag = tag;
    }

    public static Finder<Long, Subject> FIND = new Finder<>(Long.class, Subject.class);

    public Map<Long,String> getMap(Sport sport){
        Map<Long,String> subjectMap = new HashMap<>();
        for(Subject s:FIND.where().eq("sport",sport).findList()){
            subjectMap.put(s.id,s.tag);
        }
        return subjectMap;
    }

    public Subject() {
    }

    public static Subject findByTag(String tag){
        return FIND.where().eq("tag",tag).findUnique();
    }

    public static Subject findByTag(List<Subject> pilots, String tag){
        for(Subject f1p : pilots){
            if(f1p.tag.equals(tag)){return f1p;}
        }
        return findByTag(tag);
    }

    public String getRussian(){
        return get(Language.getByCode("ru"));
    }

    public String get(Language language){
        for(Translation ftt : this.translations){
            if(ftt.language.equals(language)){
                return ftt.label;
            }
        }
        return null;
    }



    public Subject(String tag, String englishLabel, String russianLabel){
        this.tag = tag;
        this.translations.add(new Translation(englishLabel, Language.getByCode("en")));
        this.translations.add(new Translation(russianLabel, Language.getByCode("ru")));
        this.save();
    }



}

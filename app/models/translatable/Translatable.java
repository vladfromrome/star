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
@MappedSuperclass
public class Translatable extends Model{
    @Id
    public Long id;


    @Column(unique = true)
    public String tag;
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Translation> translations = new ArrayList<Translation>();

    public String getTranslationByCode(String langCode) {
        for(Translation st : translations){
            if("ru".equals(st.language.code)){
                return st.label;
            }
        }
        return "noLabel";
    }

    public String getTranslation(){
         return getTranslationByCode(Language.getCurrentLanguageCode());
    }

}
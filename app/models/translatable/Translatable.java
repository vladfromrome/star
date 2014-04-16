package models.translatable;

import models.dbmessages.Language;
import play.Logger;
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
abstract public class Translatable extends Model{
    @Id
    public Long id;


    @Column(unique = true)
    public String tag;
    //@OneToMany
    @JoinColumn(name="translatable_id", referencedColumnName = "translatable_id")
    public List<Translation> translations = new ArrayList<Translation>();

    public String getTranslationByCode(String langCode) {
        Logger.info("Translations are "+translations.toString());
        for(Translation st : translations){
            if(langCode.equals(st.language.code)){
                Logger.info("checking traslation: "+st.label);
                return st.label;
            }
        }
        return "noLabel";
    }

    public String getTranslation(){
        try {
         return getTranslationByCode(Language.getCurrentLanguageCode());
        } catch (Exception e){
            return "translationExceptionMsg";
        }
    }

}

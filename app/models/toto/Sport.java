package models.toto;

import models.dbmessages.Language;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 9:26
 */
@SuppressWarnings("serial")
@Entity
public class Sport extends Translatable {

//    @Id
//    public Long id;
//
//    public String tag;


    public Sport() {
    }

    public static Sport getFootball(){
        return getByTag("football");
    }

    public static Sport getHockey(){
        return getByTag("hockey");
    }

    public static Sport getFormula1(){
        return getByTag("formula1");
    }

    public static Sport getBasketball(){
        return getByTag("basketball");
    }

    private static Sport getByTag(String tag){
        return FIND.where().eq("tag",tag).findUnique();
    }

    public Sport(String tag){
        this.tag = tag;
    }

    public Sport(String tag, String englishLabel, String russianLabel){
        this.tag = tag;
        this.translations.add(new Translation(englishLabel, Language.getByCode("en")));
        this.translations.add(new Translation(russianLabel, Language.getByCode("ru")));
        this.save();
    }

    public static Map<String,String> options(){
        LinkedHashMap<String,String> vals = new LinkedHashMap<>();
        for(Sport s : Sport.FIND.all()){
            vals.put(s.id.toString(),s.tag);
        }
        return vals;
    }

    public boolean isFootball(){
        if(this.tag.equals("football")){
            return true;
        } else {
            return false;
        }
    }

    public boolean isFormula1(){
        if(this.tag.equals("formula1")){
            return true;
        } else {
            return false;
        }
    }

    public static Sport findByTag(String tag){
        return FIND.where().eq("tag",tag).findUnique();
    }

//	@OneToMany(cascade = CascadeType.ALL)
//	public List<Translation> translations = new ArrayList<Translation>();

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Translation> translations = new ArrayList<Translation>();

    /*@OneToMany
    public List<Event> events = new ArrayList<Event>();

    @OneToMany
    public List<League> leagues = new ArrayList<League>();*/

    public static Finder<Long,Sport> FIND = new Finder<>(Long.class,Sport.class);

    public static Finder<Integer, Sport> find(){
        return new Finder<Integer,Sport>(Integer.class,Sport.class);
    }

    public String getRussian(){
        for(Translation st : translations){
            if("ru".equals(st.language.code)){
                return st.label;
            }
        }
        return "noLabel";
    }

    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Sport))return false;
        Sport s = (Sport) o;
        if(!s.tag.equals(this.tag))return false;
        return true;
    }

//    @Override
//    public int hashCode(){
//        int result = 17;
//        result = 31 * result + this.tag.hashCode();
//        return result;
//    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + this.id.hashCode();
        return result;
    }

}

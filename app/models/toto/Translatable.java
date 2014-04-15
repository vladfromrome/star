package models.toto;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Vladimir Romanov
 * Date: 15.04.14
 * Time: 10:45
 */
@MappedSuperclass
abstract public class Translatable extends Model {

    @Id
    public Long id;

    @Column(unique = true)
    public String tag;

    public static Map<Long,String> getMap(Model.Finder<Long, ? extends Translatable> FIND){
        Map<Long,String> stringMap = new HashMap<>();
        for(Translatable s: FIND.findList()){
            stringMap.put(s.id,s.tag);
        }
        return stringMap;
    }

    public static Map<String,String> getTagMap(Model.Finder<Long, ? extends Translatable> FIND){
        Map<String,String> stringMap = new HashMap<>();
        for(Translatable s: FIND.findList()){
            stringMap.put(s.tag,s.tag);
        }
        return stringMap;
    }
}

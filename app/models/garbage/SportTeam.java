package models.garbage;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mgt
 * Date: 01.04.14
 * Time: 9:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SportTeam extends Model {
    @Id public Long id;
    public static Finder<Long, SportTeam> FIND = new Finder<>(Long.class, SportTeam.class);

    public String sys_name;
    @OneToMany (mappedBy = "team",cascade = CascadeType.ALL)
    public List<SportTeam_translation> translations;



    public SportTeam(String sys_name) {
        this.sys_name = sys_name;
    }

    public void addTranslation(SportTeam_translation t) {
        this.translations.add(t);
    }

    public String name(String lang) {
        String s = "";
        SportTeam_translation t = (SportTeam_translation) SportTeam_translation.FIND
                .where()
                .eq("sportTeamId", this.id)
                .eq("lang", lang);
        return t.name;
    }

    public SportTeamTO getTO(String lang){
        return SportTeamTO.getTO(this.id,lang);
    }

}

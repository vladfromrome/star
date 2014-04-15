package models.garbage;

/**
 * Created with IntelliJ IDEA.
 * User: mgt
 * Date: 01.04.14
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class SportTeamTO {
    public Long id;

    public String sys_name;
    public String lang;
    public String name;

    public SportTeamTO(Long id, String sys_name, String lang, String name) {
        this.id = id;
        this.sys_name = sys_name;
        this.lang = lang;
        this.name = name;
    }

    public static SportTeamTO getTO(Long id, String lang){
        SportTeam st = SportTeam.FIND
                .where()
                .eq("id",id)
                .findUnique();

        SportTeam_translation t = SportTeam_translation.FIND
                .where()
                .eq("id", id)
                .findUnique();
        SportTeamTO to = new SportTeamTO(st.id,st.sys_name,t.lang,t.name);
        return to;
    }


}

package models.dbmessages;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import play.Logger;

/**
 * User: Vladimir Romanov
 * Date: 05.04.14
 * Time: 17:58
 */
@Entity
public class Language extends Model {
    @Id
    public Long id;
    public static Finder<Long,Language> FIND = new Finder<>(Long.class,Language.class);

    @Column(unique = true)
    public String name;
    public String displayName;
    @Column(unique = true)
    public String code;

    protected static final String DEFAULTLANGCODE="en";

    /**
     * Constructor autosaves new object to database!
     */
    public Language(String name, String displayName, String code) {
        this.name = name;
        this.displayName = displayName;
        this.code = code;
        this.save();
    }

    public static List<String> getSupportedLangCodes(){
        List<String> codes = new ArrayList<String>();
        for (Language l:Language.FIND.all()){
            codes.add(l.code);
        }
        return codes;
    }

    public static String getLangCodeFromReq(List<play.i18n.Lang> requestLangs){
        String code=DEFAULTLANGCODE;
        langCodesearch:
        for (play.i18n.Lang l:requestLangs){
            for (String s1:Language.getSupportedLangCodes()){
                if (l.code().substring(0,2).equals(s1)) {
                    code=s1;
                    break langCodesearch;
                }
            }
        }
        Logger.info("The language code from request is \""+code+"\"");
        return code;
    }


}

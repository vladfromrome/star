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
@SuppressWarnings("serial")
@Entity
public class Language extends Model {
    @Id
    public Integer id;
    public static Finder<Integer, Language> FIND = new Finder<>(Integer.class, Language.class);

    @Column(unique = true)
    public String name;
    public String displayName;
    @Column(unique = true)
    public String code;

    protected static final String DEFAULTLANGCODE = "en";

    /**
     * Constructor autosaves new object to database!
     */
    public Language(String name, String displayName, String code) {
        this.name = name;
        this.displayName = displayName;
        this.code = code;
        this.save();
    }

    /**
     * @return List of language codes for all Languages supported by the application
     */
    public static List<String> getSupportedLangCodes() {
        List<String> codes = new ArrayList<String>();
        for (Language l : Language.FIND.all()) {
            codes.add(l.code);
        }
        return codes;
    }

    /**
     * Finds a match for supported languages and requested by the user's browser.
     *
     * @return Code of the identified language.
     */
    public static String getLangCodeFromReq(List<play.i18n.Lang> requestLangs) {
        String code = DEFAULTLANGCODE;
        langCodesearch:
        for (play.i18n.Lang l : requestLangs) {
            for (String s1 : Language.getSupportedLangCodes()) {
                if (l.code().substring(0, 2).equals(s1)) {
                    code = s1;
                    break langCodesearch;
                }
            }
        }
        Logger.info("The language code retrieved from request is \"" + code + "\"");
        return code;
    }

    /**
     * @return Language object corresponging to the given language code.
     */
    public static Language getByCode(String langCode) {
        return FIND.where().eq("code", langCode).findUnique();
    }

    /**
     * Returns language code from cookies. If no cookies, than defines language from request and saves its code in cookies.
     */
    public static String getCurrentLanguageCode() {
        String s = DEFAULTLANGCODE;
        try {
            s = play.mvc.Http.Context.current().request().cookies().get("langCode").value();
        } catch (Exception e) {
            s=getLangCodeFromReq(play.mvc.Http.Context.current().request().acceptLanguages());
            play.mvc.Http.Context.current().response().setCookie("langCode",s);
        }
        return s;
    }

    public static Language getRussian() {
        return getByCode("ru");
    }

    public static Language getEnglish() {
        return getByCode("en");
    }


}

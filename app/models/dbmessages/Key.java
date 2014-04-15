package models.dbmessages;

import play.Logger;
import play.api.data.validation.Constraints;
import play.db.ebean.Model;
import play.i18n.Lang;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vladimir Romanov
 * Date: 05.04.14
 * Time: 17:30
 */
@Entity
public class Key extends Model {
    @Id
    public Long id;
    public static Model.Finder<Long, Key> FIND = new Finder<>(Long.class, Key.class);

    @Column(unique = true)
    public String keyword;
    public String defaultValue;
    @OneToMany(mappedBy = "key", cascade = CascadeType.ALL)
    public List<Message> messages;

    /**
     * Constructor autosaves new object to database!
     */
    public Key(String keyword, String defaultValue) {
        //Key k = Key.FIND.where().eq("keyword",keyword).findUnique();

        this.keyword = keyword;
        this.defaultValue = defaultValue;
        this.save();
    }

    /**
     * @return Key object for the given keyword.
     */
    public static Key getByKeyword(String keyword){
        return FIND.where().eq("keyword",keyword).findUnique();
    }

    /**
     * Assigns a new message to the key and saves it into DB.
     */
    public void addMessage(Language lang, String value) {
        Message m = Message.get(this,lang);
        if (m!=null){
            m.value=value;
            m.save();
        }
        else {
        Message message = new Message(this, lang, value);
        }
    }

    /**
     * @return A list of codes of languages available for the key.
     */
    private List<String> getSupportedLangCodes(){
        List<String> codes = new ArrayList<String>();
        for (Message m:messages){
            codes.add(m.lang.code);
        }
        return codes;
    }

    /**
     * @return A message from db corresponding to the given language code and keyword.
     * Returns default value if there is no translation for the given language.
     */
    public static String getMessage(String langCode, String keyword) {
        String s = "";
        try {
            Key k=Key.getByKeyword(keyword);
            try {
                Message m = Message.get(k,langCode);
                s+=m.value;
            } catch (Exception e){
                Logger.info("i18n Db messages: value not found for key \""+k.keyword+"\" and language "+"\""+Language.getByCode(langCode).displayName+"\". Return default value: \""+k.defaultValue+"\"");
                s+="DefaultValue: "+ k.defaultValue;
            }
        }  catch (Exception e){
            s+="Key not found: \""+keyword+"\"";
        }
        return s;
    }

}

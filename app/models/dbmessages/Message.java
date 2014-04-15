package models.dbmessages;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * User: Vladimir Romanov
 * Date: 05.04.14
 * Time: 17:35
 */
@Entity
public class Message extends Model {
    @Id
    public Long id;
    public static Finder<Long,Message> FIND = new Finder<>(Long.class, Message.class);

    @ManyToOne
    public Key key;
    @ManyToOne
    public Language lang;
    public String value;

    /**
     * Constructor autosaves new object to database!
     */
    public Message(Key key, Language lang, String value) {
        this.key = key;
        this.lang = lang;
        this.value = value;
        this.save();
    }

    /**
     * Get Message by Key object and Language object
     */
    public static Message get(Key key,Language lang){
        return FIND.where().eq("key",key).eq("lang",lang).findUnique();
    }

    /**
     * Get Message by Key object and langCode
     */
    public static Message get(Key key, String langCode){
        return FIND.where().eq("key",key).eq("lang.code",langCode).findUnique();
    }

}

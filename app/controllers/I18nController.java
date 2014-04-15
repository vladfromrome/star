package controllers;

import com.avaje.ebean.Ebean;
import models.dbmessages.Key;
import models.dbmessages.Language;
import models.dbmessages.Message;
import models.translatable.Sport;
import play.Logger;
import play.mvc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Vladimir Romanov
 * Date: 05.04.14
 * Time: 17:46
 */
public class I18nController extends Controller {
    //Later: можно добавить java.text.MessageFormat
    private static final String hosturl="http://localhost:9000/";

    public static String getMsg(String keyword) {
        String langCode="en";
        if (request().cookies().get("langCode")==null)     {
            Logger.info("I18nController: No language cookie detected. Getting language from request.");
            langCode= Language.getLangCodeFromReq(request().acceptLanguages());
            response().setCookie("langCode",langCode);
        }  else {
            langCode=request().cookies().get("langCode").value();
        }
        return Key.getMessage(langCode, keyword);
    }

    public static Result action() {
        deleteAll();
        populate();
        return ok("OK");
    }

    /**
     * @return A String of links to set language.
     */
    public static String langLinks() {
        String s = "";
        for (String code: Language.getSupportedLangCodes()){
            s+="<a href='"+hosturl+code+"'>"+Language.getByCode(code).displayName+"</a>&#160;&#160;&#160;";
        }
        return s;
    }

    public static void populate() {
        //List<String> codes= Arrays.asList("en","en-Us");
        Language en = new Language("english", "English", "en");
        Language ru = new Language("russian", "Русский", "ru");
        Language it = new Language("italian", "Italiano", "it");


        Key title = new Key("title", "defaultBetsterTitleMessageFromDb");
        title.addMessage(en, "BetsterTitleMessageFromDb");
        title.addMessage(ru, "БетстерТитульноеСообщениеИзБд");
        title.addMessage(it, "BetsterMessagioTitoloDaBd");

        Key hello = new Key("hello", "defaulthellotestmsgFromDb");
        hello.addMessage(en, "hellotestmsgFromDb");
        hello.addMessage(ru, "приветтестовоесообщениеИзБд");
        hello.addMessage(it, "ciaomessagiotestDaBd");

        Sport football = new Sport("football","Football","Футбол");
        Sport volleyball = new Sport("volleyball","Volleyball","Волейбол");
        Sport tennis = new Sport("tennis","Tennis","Теннис");

        football.save();
        volleyball.save();
        tennis.save();
    }

    public static void deleteAll() {
        List<Message> messages = Message.FIND.all();
        Ebean.delete(messages);
        List<Key> keys = Key.FIND.all();
        Ebean.delete(keys);
        List<Language> languages = Language.FIND.all();
        Ebean.delete(languages);

    }
}

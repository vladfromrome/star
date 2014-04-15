package controllers;

import models.dbmessages.Language;
import play.*;
import play.mvc.*;

public class Application extends Controller {
    public static Result defaultAction() {
        return ok("есть контакт!");
    }

    public static Result i18nIndex(String langCode){
        Logger.info("Application.i18nIndex(): langCode from get link: \""+langCode+"\"");
        if (Language.getByCode(langCode)==null) langCode="en";
        response().setCookie("langCode",langCode);
        Logger.info("Application.i18nIndex(): langCode is set to: \""+langCode+"\"");
        return redirect("/");
    }

    public static Result index() {
        Logger.info("Applicaiton: index() action is triggered.");
        I18nController.deleteAll();
        I18nController.populate();
        if (request().cookies().get("langCode")==null)     {
            Logger.info("Application.index(): No language cookie detected. Getting language from request.");
            response().setCookie("langCode",Language.getLangCodeFromReq(request().acceptLanguages()));
            return redirect("/");
        }

        String i18nFromDbmessage = I18nController.getMsg("title");

        return ok(views.html.index.render(i18nFromDbmessage));
    }



}


/*public static Result sport() {
        return controllers.Application.index();
    }

    public static void populate(){
        SportTeam a = new SportTeam("zenit");
        a.addTranslation(new SportTeam_translation("en","ZENIT",a));
        a.addTranslation(new SportTeam_translation("ru","ЗЕНИТ",a));
        a.save();
        SportTeam b = new SportTeam("spartak");
        b.addTranslation(new SportTeam_translation("en","SPARTAK",b));
        b.addTranslation(new SportTeam_translation("ru","СПАРТАК",b));
        b.save();
    }*/
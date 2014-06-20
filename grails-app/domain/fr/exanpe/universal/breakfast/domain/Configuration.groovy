package fr.exanpe.universal.breakfast.domain

import grails.util.Holders
import org.springframework.context.i18n.LocaleContextHolder

class Configuration {

    Boolean sendMail = true

    String prepareMail
    String prepareMailSubject
    String togetherMail
    String togetherMailSubject

    Boolean cardEnabled = true
    Boolean planningEnabled = true

    //add more configuration

    static belongsTo = [team:Team]

    static constraints = {
    //    created on beforeInsert
    //    prepareMail nullable: false
    //    togetherMail nullable: false
    }

    def beforeInsert = {
        restoreDefault()
    }

    def restoreDefault(){
        sendMail = true

        prepareMail = this.class.classLoader.getResource(Holders.grailsApplication.config.ub.template.mail.prepare.toString()).text;
        togetherMail = this.class.classLoader.getResource(Holders.grailsApplication.config.ub.template.mail.together.toString()).text;
        prepareMailSubject = Holders.applicationContext.getMessage("ub.mail.prepare.subject", null, LocaleContextHolder.locale)
        togetherMailSubject = Holders.applicationContext.getMessage("ub.mail.together.subject", null, LocaleContextHolder.locale)

        cardEnabled = true;
        planningEnabled = true;

        return this;
    }

}

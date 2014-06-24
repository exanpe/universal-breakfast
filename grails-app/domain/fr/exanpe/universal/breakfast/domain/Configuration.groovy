package fr.exanpe.universal.breakfast.domain

import grails.util.Holders
import org.springframework.context.i18n.LocaleContextHolder

class Configuration {

    Boolean sendMail = true

    String prepareMail
    String prepareMailSubject
    String gatheringMail
    String gatheringMailSubject

    Boolean cardEnabled = true
    Boolean planningEnabled = true

    Team team

    //add more configuration

    static belongsTo = [team:Team]

    static constraints = {
    //    created on beforeInsert
    //    prepareMail nullable: false
    //    gatheringMail nullable: false
    }

    def beforeInsert = {
        restoreDefault()
    }

    def restoreDefault(){
        sendMail = true

        prepareMail = this.class.classLoader.getResource(Holders.grailsApplication.config.ub.template.mail.prepare.toString()).text;
        gatheringMail = this.class.classLoader.getResource(Holders.grailsApplication.config.ub.template.mail.gathering.toString()).text;
        prepareMailSubject = Holders.applicationContext.getMessage("ub.mail.prepare.subject", null, LocaleContextHolder.locale)
        gatheringMailSubject = Holders.applicationContext.getMessage("ub.mail.gathering.subject", null, LocaleContextHolder.locale)

        cardEnabled = true;
        planningEnabled = true;

        return this;
    }

}

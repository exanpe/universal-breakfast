import grails.util.Holders

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [
        "file:${userHome}/.grails/ub.groovy",
        "file:${userHome}/.grails/ub.properties",
        "file:"+System.getenv("OPENSHIFT_DATA_DIR")+"ub.properties"
]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = "fr.exanpe" // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password', 'password2']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

grails.gorm.default.constraints = {
    '*'(nullable: true)
}
grails.gorm.failOnError = true

grails.databinding.dateFormats = ['MM-dd-yyyy', 'dd/MM/yyyy']

ub.paginate.items.perPage=10
grails.plugins.twitterbootstrap.fixtaglib = true

ub.template.mail.prepare = "mails/prepareMail.html"
ub.template.mail.gathering = "mails/gatheringMail.html"

ub.mail="exanpe@gmail.com"
ub.github="https://github.com/exanpe/universal-breakfast"

grails {
    mail {
        host = "localhost"
        port = 25
        username = ub.mail
        password = ""
    }
}
grails.mail.default.from = ub.mail

templates = {
    props{
        propGlobal id:"url", templateKey : "_URL_", script : {Holders.config.grails.serverURL}
        propGlobal id:"teamname", templateKey : "_TEAM_NAME_", script : {Holders.grailsApplication.mainContext.springSecurityService.currentUser.username}
        prop id:"breakfastdate", templateKey : "_BREAKFAST_DATE_", script : {(it && it["breakfastdate"])?it["breakfastdate"]:""}
        prop id:"message", templateKey : "_MESSAGE_", script : {(it && it["message"])?it["message"]:""}
        prop id:"location", templateKey : "_LOCATION_", script : {(it && it["location"])?it["location"]:""}
    }

    template id:"prepare", props : ["breakfastdate", "message"]
    template id:"gather", props : ["location", "message"]
}

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.plugin.springsecurity.debug.useFilter = true

        // Session monitoring conf
        ub.session.max.count = 5
        grails.serverURL = "http://localhost:8080"
        ub.security.salt = "salt"//just a development value
    }

    test{
        grails.mail.disabled=true
        grails.serverURL = "http://www.ub-test.com"
        ub.security.salt = "salt"//just a development value
    }

    production {
        grails.logging.jul.usebridge = false
        //TODO JMX change with final domain
        grails.serverURL = "http://ub-exanpe.rhcloud.com"

        // Session monitoring conf
        ub.session.max.count = 100

        //ub.security.salt = "OVERWRITE"

        grails {
            mail {
                host = "smtp.gmail.com"
                port = 465
                username = ub.mail
                // password = "OVERWRITE"
                props = ["mail.smtp.auth":"true",
                        "mail.smtp.socketFactory.port":"465",
                        "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                        "mail.smtp.socketFactory.fallback":"false"]

            }
        }
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
   // debug    'org.springframework.security'

}

// LESS compiler
grails.assets.less.compiler = "less4j"

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.exanpe.universal.breakfast.domain.Team'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.exanpe.universal.breakfast.domain.TeamRole'
grails.plugin.springsecurity.authority.className = 'fr.exanpe.universal.breakfast.domain.Role'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/route'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        '/':                                ['permitAll'],
        '/assets/**':                       ['permitAll'],
        '/index':                           ['permitAll'],
        '/messages/**':                     ['permitAll'],
        '/home/**':                         ['permitAll'],
        '/**/js/**':                        ['permitAll'],
        '/**/css/**':                       ['permitAll'],
        '/**/images/**':                    ['permitAll'],
        '/**/favicon.ico':                  ['permitAll'],
        '/simpleCaptcha/**':                ['permitAll'],
        '/jawr/**':                         ['permitAll'],
        '/register/**':                     ['permitAll'],
        '/planning/**':                      ['permitAll'],//planning
        '/card/**':                         ['permitAll'],//card
        //user URL
        '/complete/**':                      ['ROLE_USER'],
        '/gather/**':                        ['ROLE_USER'],
        '/manage/**':                        ['ROLE_USER'],
        '/prepare/**':                       ['ROLE_USER'],
        '/route/**':                         ['ROLE_USER'],
        '/account/**':                         ['ROLE_USER'],
        '/history/**':                         ['ROLE_USER'],
        //admin URL
        '/**':                               ['ROLE_ADMIN']
]

// Allow GET to trigger logout
grails.plugin.springsecurity.logout.postOnly = false

// Enable event publishing
grails.plugin.springsecurity.useSecurityEventListener = true

grails.app.context = "/"

// Simple Captcha conf
simpleCaptcha {
    // Font size used in CAPTCHA images
    fontSize = 18
    height = 200
    width = 200

    // Number of characters in CAPTCHA text
    length = 6

    //no session if not loggued
    storeInSession = false
}

// GA conf
google.analytics.webPropertyID = "UA-xxxxxx-x"

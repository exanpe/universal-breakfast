grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    test: false,
    run: false
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://repo.spring.io/milestone/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.27'
        // runtime 'org.postgresql:postgresql:9.3-1100-jdbc41'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.52.1"

        // plugins for the compile step
        compile ":scaffolding:2.0.2"
        compile ':cache:1.1.1'

        // plugins needed at runtime but not for compilation
        //runtime ":hibernate:3.6.10.9" // or
        runtime ":hibernate4:4.3.4"
        runtime ":database-migration:1.3.8"
        runtime ":jquery:1.11.0.2"
        runtime ":resources:1.2.7"
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0.1"
        //runtime ":cached-resources:1.1"
        //runtime ":yui-minify-resources:0.1.5"

        // An alternative to the default resources plugin is the asset-pipeline plugin
        compile ":asset-pipeline:1.8.4"
        compile ":less-asset-pipeline:1.7.0"
        //compile ":handlebars-asset-pipeline:1.3.0.1"
        runtime ':twitter-bootstrap:3.1.1'

        // Spring plugins
        compile ":spring-security-core:2.0-RC2"

        // Captcha
        compile ":simple-captcha:0.9.9"
    }
}

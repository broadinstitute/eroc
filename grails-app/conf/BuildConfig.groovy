grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.war.dependencies = ["ojdbc14_g.jar"]
grails.project.war.file = "target/ComplianceChecker.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
//        Environment.executeForCurrentEnvironment {
//            production {
//                excludes "grails-plugin-log4j", "log4j"
//            }
//        }
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
            grailsPlugins()
            grailsHome()
            grailsCentral()
            mavenRepo "http://cbip-repo:8081/artifactory/repo"
            grailsRepo "http://cbip-repo:8081/artifactory/svn.codehaus.org_grails-plugins"
            grailsRepo "http://cbip-repo:8081/artifactory/svn.codehaus.org_grails_trunk_grails-plugins"
    }
    dependencies {
        compile 'com.oracle:ojdbc6:11.2.0.2.0'   //this is our oracle jar file in our artifactory repos
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.5"

        build ":tomcat:$grailsVersion"
    }
}

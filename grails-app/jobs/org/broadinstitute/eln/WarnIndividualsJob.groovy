package org.broadinstitute.eln

import grails.plugin.mail.MailService

class WarnIndividualsJob {
    MailService mailService
    ElnOracleCheckerService elnOracleCheckerService

    static triggers = {
        simple startDelay: 200l, repeatInterval: 500000l // execute job once in 5 seconds
//        cron name: 'warnIndividualsTrigger', cronExpression: "0 15 7 ? * * *"  // send a report to all individuals at six a.m. each morning
    }



    def execute() {
        // Check our listed jobs, and execute each one
        ApplicationSharedValues applicationSharedValues = ApplicationSharedValues.getInstance()
        if ( applicationSharedValues.sendEmailToIndividuals ||
                applicationSharedValues.writeArchiveFilesForIndividuals ) {
            CommonLibraryRoutines.getInstance().doWorkForIndividualReports(mailService,elnOracleCheckerService)
        }
    }


}


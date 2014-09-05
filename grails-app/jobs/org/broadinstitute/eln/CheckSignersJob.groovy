package org.broadinstitute.eln

import grails.plugin.mail.MailService



class CheckSignersJob {
    MailService mailService
    ElnOracleCheckerService elnOracleCheckerService

    static triggers = {
        simple startDelay: 300l, repeatInterval: 500000l // execute job once in 5 seconds for testing
//        cron name: 'checkSignersTrigger', cronExpression: "0 30 1 ? * SAT *"   // send a report to the managers at 1:30 every Saturday morning
    }

    def execute() {
        ApplicationSharedValues applicationSharedValues = ApplicationSharedValues.getInstance()
        if ( applicationSharedValues.sendEmailToManagers ||
                applicationSharedValues.writeArchiveFilesForManagers ) {
            CommonLibraryRoutines.getInstance().doWorkForManagersReports(mailService,elnOracleCheckerService)
        }
        if ( applicationSharedValues.sendEmailToManagers ||
                applicationSharedValues.writeArchiveFilesForManagers ) {
            CommonLibraryRoutines.getInstance().doWorkForManagersWitnessReports(mailService,elnOracleCheckerService)
        }

    }
}


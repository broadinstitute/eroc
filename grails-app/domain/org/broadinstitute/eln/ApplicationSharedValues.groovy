package org.broadinstitute.eln

/**
 *  Store the values that are shared by the application. This is a Singleton, but implemented
 *  as a domain object in order to take advantage of the automatic controller and view
 *  machinery.
 */
class ApplicationSharedValues {
    
    static long instanceId = 1l
    static  ApplicationSharedValues instance

    /**
     * Here is the static method that allows access to the Singleton
     * @return
     */
    static synchronized ApplicationSharedValues getInstance() {
        if (!instance) {
            instance = ApplicationSharedValues.get(instanceId)
            if (!instance) {
                instance = new ApplicationSharedValues()
                instance.id = instanceId
                instance.save()
            }
        }
        instance
    }


    String earliestDateConsidered =  "2012/01/31:12:00:00AM"
    Integer  daysAllowedForSigning = 30
    Integer  daysAllowedForCountersigning = 30
    Integer timeIntervalOverWhichUsersAreNotified  = 1
    Integer daysBeforeIndividualWarning  = 25
    String [] pilotsToReceiveManagerEmail = []
    String pathwayToStoreErocForIndividuals = "C:/ErocRecords" // don't need to store individual reports -- these are only mornings
   // String pathwayToStoreErocForIndividuals = "\\\\o\\cbnt_eln_archive_stage\\201203"
    String [] pilotsToReceiveIndividualEmail = []
    Boolean sendEmailToIndividuals = false
    Boolean writeArchiveFilesForIndividuals = false
   String pathwayToStoreErocForManagers = "C:/ErocRecords"
//    String pathwayToStoreErocForManagers = "\\\\o\\cbnt_eln_archive_stage"   // manager reports go to our archival location
    Boolean sendEmailToManagers = false
    Boolean writeArchiveFilesForManagers = true
    String erocAdministratorEmailAddress = "balexand@broadinstitute.org"

    static constraints = {
        earliestDateConsidered(blank: false)
        daysAllowedForSigning( min: 0)
        daysAllowedForCountersigning( min: 0)
        sendEmailToIndividuals()
        sendEmailToManagers ()
        pathwayToStoreErocForIndividuals(blank: false)
        pathwayToStoreErocForManagers(blank: false)
        Integer timeIntervalOverWhichUsersAreNotified
    }
}

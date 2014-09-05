package org.broadinstitute.eln

class ComplyProcess {
    String name
    String[] mailRecipients
    String mailSubjectLine
    String[] statesOfInterest
    String[] referencedSubgroups
    String earliestDateOfInterest
    Integer daysAllowedForSigning

    static constraints = {
        name(blank: false)
        mailRecipients(blank: true)
        mailSubjectLine(blank: true)
        statesOfInterest(blank: false)
        referencedSubgroups(blank: false)
        earliestDateOfInterest(blank: false)
        daysAllowedForSigning(min:0)
     }

    static String[] pilotModeUsers   = []


}

package org.broadinstitute.eln

class PersonalComplyProcess extends ComplyProcess {
    String individual
    static constraints = {
        individual(blank: false)
    }

    static String[] pilotModeUsers   = []
}

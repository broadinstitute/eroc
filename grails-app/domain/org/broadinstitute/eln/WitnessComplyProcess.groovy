package org.broadinstitute.eln

class WitnessComplyProcess  extends ComplyProcess {
    Integer daysAllowedForCounterSigning

    static constraints = {
        daysAllowedForCounterSigning(min:0)
    }
}

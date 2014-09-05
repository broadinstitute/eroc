import org.broadinstitute.eln.ComplyProcess
import org.broadinstitute.eln.PersonalComplyProcess
import org.broadinstitute.eln.ApplicationSharedValues
import org.broadinstitute.eln.WitnessComplyProcess

class BootStrap {

    def init = { servletContext ->
        quartz {
            autoStartup = true
            jdbcStore = false
        }
        ApplicationSharedValues applicationSharedValues = ApplicationSharedValues.getInstance()
        String earliestDateConsidered =  "2012/01/31:12:00:00AM"

        (new ComplyProcess(name: "Platform chemistry, signing",
                mailRecipients: ["jduvall@broadinstitute.org"],
                mailSubjectLine: "Platform chemistry, signing report",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Platform Chemistry"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning)).save()
        (new ComplyProcess(name: "Stanley Center, signing",
                mailRecipients: ["edholson@broadinstitute.org", "mweiwer@broadinstitute.org", "fwagner@broadinstitute.org"],
                mailSubjectLine: "Stanley Center, signing report",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Stanley Center", "Sai Advantium"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning)).save()
        (new ComplyProcess(name: "Medicinal chemistry, signing",
                mailRecipients: ["sivaram@broadinstitute.org", "bmunoz@broadinstitute.org"],
                mailSubjectLine: "Medicinal chemistry, signing report",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Organic Synthesis Fellows"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning)).save()

        (new WitnessComplyProcess(name: "Platform chemistry, witnessing",
                mailRecipients: ["jduvall@broadinstitute.org"],
                mailSubjectLine: "Platform chemistry, witnessing report",
                statesOfInterest: ["Signed", "Signed-ReOpened"],
                referencedSubgroups: ["Platform Chemistry"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning,
                daysAllowedForCounterSigning: applicationSharedValues.daysAllowedForCountersigning)).save()
        (new WitnessComplyProcess(name: "Stanley Center, witnessing",
                mailRecipients: ["edholson@broadinstitute.org", "mweiwer@broadinstitute.org", "fwagner@broadinstitute.org"],
                mailSubjectLine: "Stanley Center, witnessing report",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Stanley Center", "Sai Advantium"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning,
                daysAllowedForCounterSigning: applicationSharedValues.daysAllowedForCountersigning)).save()

        (new WitnessComplyProcess(name: "Medicinal chemistry, witnessing",
                mailRecipients: ["sivaram@broadinstitute.org", "bmunoz@broadinstitute.org"],
                mailSubjectLine: "Medicinal chemistry, witnessing report",
                statesOfInterest: ["Signed", "Signed-ReOpened"],
                referencedSubgroups: ["Organic Synthesis Fellows"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning: applicationSharedValues.daysAllowedForSigning,
                daysAllowedForCounterSigning: applicationSharedValues.daysAllowedForCountersigning)).save()

    }
    def destroy = {
    }
}

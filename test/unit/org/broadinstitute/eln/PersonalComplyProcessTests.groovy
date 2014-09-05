package org.broadinstitute.eln



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PersonalComplyProcess)
class PersonalComplyProcessTests {

    void testSomething() {
        def personalComplyProcess = new PersonalComplyProcess(individual: 'Ben_Testing1',
                name: "Program, signing",
                mailRecipients: ["balexand@broadinstitute.org","benjamin.alexander96@yahoo.com"],
                mailSubjectLine: "Program, signing",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Program Chemistry", "CCBR Members"],
                earliestDateOfInterest:  "2011/12/20:12:00:00AM",
                daysAllowedForSigning: 15)
        assertEquals  personalComplyProcess.getClass().name,"org.broadinstitute.eln.PersonalComplyProcess"
        assertEquals  personalComplyProcess.errors.allErrors.size(),0
      //  assertEquals  personalComplyProcess.findAllUsersWithEmailSQL(), "select username,email_address from ELN_people where email_address is not null"
       // fail "Implement me"
    }
}

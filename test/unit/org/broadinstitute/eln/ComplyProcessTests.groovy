package org.broadinstitute.eln


import org.codehaus.groovy.grails.test.GrailsTestTargetPattern;
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunner;

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(ComplyProcess)



import grails.test.*

import org.junit.Test;
import org.junit.runner.RunWith;
import org.broadinstitute.eln.GrailsJUnit4Runner;

@RunWith(GrailsJUnit4Runner)
class ComplyProcessTests  extends GrailsUnitTestCase {

    @Test
    void testBuildAComplyProcessObject() {
        def complyProcess = new ComplyProcess(name: "Program, signing",
                mailRecipients: ["balexand@broadinstitute.org","benjamin.alexander96@yahoo.com"],
                mailSubjectLine: "Program, signing",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Program Chemistry", "CCBR Members"],
                earliestDateOfInterest:  "2012/02/01:12:00:00AM",
                daysAllowedForSigning: 25)
        assertEquals  complyProcess.getClass().name,"org.broadinstitute.eln.ComplyProcess"
        assertEquals  complyProcess.errors.allErrors.size(),0
    }

    @Test
    void testHoldUsersAndRelatedInfo() {
        def holdUsersAndRelatedInfo = new HoldUsersAndRelatedInfo( elnUsername:"jtesting" ,
                nameOfHuman:"Joe Testing",
                nameOfGroup:"Platform" ,
                emailAddress :"a" )
        assertEquals  holdUsersAndRelatedInfo.getClass().name,"org.broadinstitute.eln.HoldUsersAndRelatedInfo"
    }


    @Test
    void test() {
        def complyProcess = new ComplyProcess(name: "Program, signing",
                mailRecipients: ["balexand@broadinstitute.org","benjamin.alexander96@yahoo.com"],
                mailSubjectLine: "Program, signing",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Program Chemistry", "CCBR Members"],
                earliestDateOfInterest:  "2011/12/20:12:00:00AM",
                daysAllowedForSigning: 5)
        assertEquals  complyProcess.getClass().name,"org.broadinstitute.eln.ComplyProcess"
        assertEquals  complyProcess.errors.allErrors.size(),0
    }


}

package org.broadinstitute.eln

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ElnOracleCheckerService)
class ElnOracleCheckerServiceTests extends GroovyTestCase{
    def elnOracleCheckerService

    void setUp() {
        elnOracleCheckerService = new ElnOracleCheckerService()
    }


    void testServiceComplyProcessCall() {
        def  daysAllowedForAction = 0
        String earliestDateConsidered =  "2011/12/20:12:00:00AM"
        def complyProcess = new ComplyProcess(name: "Platform chemistry, signing",
                mailRecipients: ["balexand@broadinstitute.org","benjamin.alexander96@yahoo.com"],
                mailSubjectLine: "Platform chemistry, signing",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Platform Chemistry", "Organic Synthesis Fellows", "Screening Users", "Analytical Members", "Administrators", "Malaria", "Novel Therapeutics"],
                earliestDateOfInterest: earliestDateConsidered,
                daysAllowedForSigning:daysAllowedForAction)
        String str = elnOracleCheckerService.getData(complyProcess)
        assertNotNull "Error -- we expected some response from the database",str
        assertTrue "Error -- we expected some records corresponding to Unsigned users",str.size()>0
    }

    void testGetListOfUsers() {
        List <HoldUsersAndRelatedInfo> holdUsersAndRelatedInfoList = elnOracleCheckerService.getListOfUsers()
        assertNotNull "Error -- we expected some response from the database",holdUsersAndRelatedInfoList
        assertTrue "Error -- we expected some records corresponding to Active users",holdUsersAndRelatedInfoList.size()>0
    }


/*
    void testServicePersonalComplyProcessCall() {
        def  daysAllowedForAction = 5
        String earliestDateConsidered =  "2011/12/20:12:00:00AM"
        new PersonalComplyProcess(individual: 'Ben_Testing1',
                name: "Program, signing",
                mailRecipients: ["balexand@broadinstitute.org","benjamin.alexander96@yahoo.com"],
                mailSubjectLine: "Program, signing",
                statesOfInterest: ["Open", "Re-Opened"],
                referencedSubgroups: ["Program Chemistry", "CCBR Members"],
                earliestDateOfInterest:  "2011/12/20:12:00:00AM",
                daysAllowedForSigning: 15)
        def str = elnOracleCheckerService.getData(complyProcess)
        assertNotNull "Error -- we expected some response from the database",str
    }
*/

}

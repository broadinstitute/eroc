package org.broadinstitute.eln

import org.junit.Test;
import org.junit.runner.RunWith;
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern;
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunner;

import grails.test.mixin.*
import org.junit.*
import grails.test.GrailsUnitTestCase

/**
 * Created by IntelliJ IDEA.
 * User: balexand
 * Date: 3/6/12
 * Time: 7:00 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GrailsJUnit4Runner)
class CommonSrcTests  extends GrailsUnitTestCase {

    @Test
    void testProvideADatedSubdirectory() {
        CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
        String directoryName  = commonLibraryRoutines.provideADatedSubdirectory("c:/temp")
        File directoryWeJustCreated = new File(directoryName)
        assertTrue (directoryWeJustCreated.exists() && directoryWeJustCreated.isDirectory())
        assertTrue directoryWeJustCreated.deleteDir()
    }
    
    @Test
    void testProvideADailyDatedSubdirectory() {
        CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
        String directoryName  = commonLibraryRoutines.provideADatedSubdirectory("c:/temp")
        File directoryWeJustCreated = new File(directoryName)
        assertTrue (directoryWeJustCreated.exists() && directoryWeJustCreated.isDirectory())
        assertTrue directoryWeJustCreated.deleteDir()
    }

    @Test
    void provideAComboDatedSubdirectory() {
        CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
        String directoryName  = commonLibraryRoutines.provideADailySubdirectory( commonLibraryRoutines.provideADatedSubdirectory("c:/temp") )
        File directoryWeJustCreated = new File(directoryName)
        assertTrue (directoryWeJustCreated.exists() && directoryWeJustCreated.isDirectory())
        assertTrue directoryWeJustCreated.deleteDir()        
    }

}

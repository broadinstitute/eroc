package org.broadinstitute.eln

import grails.test.mixin.TestFor

/**
 * Created by IntelliJ IDEA.
 * User: balexand
 * Date: 3/5/12
 * Time: 8:43 AM
 * To change this template use File | Settings | File Templates.
 */
//@TestFor(CommonLibraryRoutines)
class TestCommonLibraryRoutines {
    CommonLibraryRoutines commonLibraryRoutines
    
    void setUp() {
        commonLibraryRoutines = CommonLibraryRoutines.getInstance()
    }


    void testProvideADatedSubdirectory() {
        String directoryName  = commonLibraryRoutines.provideADatedSubdirectory("c:/temp")
        File directoryWeJustCreated = new File(directoryName)
        assertTrue (directoryWeJustCreated.exists() && directoryWeJustCreated.isDirectory())
        assertTrue directoryWeJustCreated.deleteDir()
    }

    
}

package org.broadinstitute.eln


import grails.test.*

import org.junit.Test;
import org.junit.runner.RunWith;

import grails.test.mixin.*
import org.junit.*
import javax.validation.constraints.AssertTrue

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ApplicationSharedValues)
class ApplicationSharedValuesTests {

    @Test
    void testApplicationSharedValues() {
        ApplicationSharedValues applicationSharedValues =  ApplicationSharedValues.getInstance()
        assertEquals applicationSharedValues.daysAllowedForSigning,30
    }
}

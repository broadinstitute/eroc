package org.broadinstitute.eln
/**
 * Created by IntelliJ IDEA.
 * User: balexand
 * Date: 2/15/12
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern;
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunner;
@SuppressWarnings("rawtypes")
public class GrailsJUnit4Runner extends GrailsTestCaseRunner {
    public GrailsJUnit4Runner(Class testClass) {
        super(testClass, new GrailsTestTargetPattern[0]);
    }
}
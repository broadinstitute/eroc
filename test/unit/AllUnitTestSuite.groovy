
/**
 * Created by IntelliJ IDEA.
 * User: balexand
 * Date: 2/15/12
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
import junit.framework.Test

public class AllUnitTestSuite extends AllTestSuite {

    private static final String BASEDIR = "./test/unit"
    private static final String PATTERN = "**/*Tests.groovy"

    public static Test suite() {
        return suite(BASEDIR, PATTERN)
    }

}

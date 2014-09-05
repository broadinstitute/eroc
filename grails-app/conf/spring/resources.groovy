import org.apache.commons.dbcp.BasicDataSource
import oracle.jdbc.OracleDriver

// Place your Spring DSL code here
beans = {

    dataSourceOracle(BasicDataSource) {
        driverClassName = "oracle.jdbc.OracleDriver"
       // url = "jdbc:oracle:thin:@//choline.broad.mit.edu:1521/chemtest"  // QA
         url =  "jdbc:oracle:thin:@//vora01:1521/chemnote"     // production
        username = "cs_notebook9"
        password = "cs_notebook9"
    }


}

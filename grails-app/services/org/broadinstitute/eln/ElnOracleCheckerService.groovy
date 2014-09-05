package org.broadinstitute.eln

import groovy.sql.Sql


class HoldUsersAndRelatedInfo {
    String elnUsername
    String nameOfHuman
    String nameOfGroup
    String emailAddress
}



/**
 * This is the class that encapsulates the SQL we used to make the query.  The SQL is
 * parameterized in various ways, so this single query is able to handle all the different
 * we need to get out of the database.  Note that we are performing multiple database queries,
 * (each of which gives us back only the data we want) Rather than pulling back a smaller
 * number of queries and dividing up the data with Groovy. The idea here is that if the
 * quantity of data coming back becomes large that the database queries will still
 * be operational ( if slow), whereas performing the division and high-level language
 * is always going to run the potential of a stack overflow.
 */
class ElnDatabaseHitter {

    /***
     * Take a list of elements and reform them into into a large OR clause, suitable
     * for digestion by a SQL.
     * @param stringToCompare
     * @param elementsInCollection
     * @return
     */
    static String produceAndOrString(String stringToCompare, String[] elementsInCollection) {
        StringBuffer stringBuffer = new StringBuffer(" and ( ")
        int elementCount = elementsInCollection.size()
        int loopingVariable = 1
        elementsInCollection.each { anElement ->
            stringBuffer << "(" + stringToCompare + "='" + anElement + "')"
            if (loopingVariable < elementCount) {
                stringBuffer << "\n    or "
                loopingVariable++
            } else {
                stringBuffer << ")"
            }
        }
        stringBuffer.toString()
    }



    static String sqlToRetrieveTheUsers () {
       """
select ep.username as "username", eln_collections.name as "nameOfHuman", ec2.name as "nameOfGroup", ep.email_address as "emailAddress" from eln_people ep
inner join eln_collections on ep.home_collection_key=eln_collections.section_set_key
inner join eln_references on eln_references.primary_key=eln_collections.container_key
inner join eln_collections ec2 on eln_references.collection_key=ec2.section_set_key
where (eln_collections.is_active = 1)
 and ep.email_address is not null
order by ep.username
       """
    }



    static String mySqlString(PersonalComplyProcess personalComplyProcess, int daysInTimeInterval, int daysUntilWarningTakesPlace ) {
        String additionWhereQuery =  " and (eln_people.username='${personalComplyProcess.individual}') \n"+
           "and (ena_versions_creation.action_date < (sysdate- INTERVAL '${daysUntilWarningTakesPlace} 00:00:00.0' day to second(1)) ) \n" +
           "and (ena_versions_creation.action_date > (sysdate- INTERVAL '${daysUntilWarningTakesPlace+daysInTimeInterval} 00:00:00.0' day to second(1)) )";
        mySqlString(personalComplyProcess as ComplyProcess, additionWhereQuery )
    }




    static String mySqlString(ComplyProcess complyProcess, String restrictUser) {
         String sqlString = """
select
     eln_collections.name as "Experiment_name"
     ,eln_states.name  as "state"
     ,eln_people.username  as "username"
     ,eln_collections2.name   as "full_name"
     ,ena_versions_creation.action_date as "create"
     ,eln_collections3.name as "group"
     ,round(sysdate - TO_DATE (TO_CHAR (ena_versions_creation.action_date, 'YYYY-MON-DD HH24:MI:SS'),
                'YYYY-MON-DD HH24:MI:SS'
                 ),1) as "days_since_creation"
     from eln_collections eln_collections
inner join eln_states on eln_states.primary_key=eln_collections.state_key
inner join eln_collection_types on eln_collection_types.section_set_key =eln_collections.collection_type_key
inner join eln_people on eln_people.primary_key =eln_collections.owner_key
inner join eln_collections eln_collections2 on eln_collections2.section_set_key=eln_people.home_collection_key
inner join eln_references on eln_references.primary_key=eln_collections2.container_key
inner join eln_collections eln_collections3 on eln_collections3.section_set_key=eln_references.collection_key
inner join eln_references eln_references2 on eln_references2.primary_key=eln_collections3.container_key
inner join eln_collections eln_collections4 on eln_collections4.section_set_key=eln_references2.collection_key
inner join ena_versions ena_versions_creation on ena_versions_creation.primary_key=eln_collections.creation_version
inner join ena_versions ena_versions_lastmod  on ena_versions_lastmod.primary_key=eln_collections.last_modified_version
where
    (ena_versions_creation.action_date> to_date('""" + complyProcess.earliestDateOfInterest + """', 'yyyy/mm/dd:hh:mi:ssam'))
    """+restrictUser+""" and (ena_versions_creation.action_date < (sysdate- INTERVAL '""" + complyProcess.daysAllowedForSigning.toString() + """ 00:00:00.0' day to second(1)) )
    and (eln_collection_types.plural_name = 'Chemistry Experiments')
    and (eln_collection_types.is_active = 1)
    """ + produceAndOrString("eln_states.name", complyProcess.statesOfInterest) + """
    """ + produceAndOrString("eln_collections3.name", complyProcess.referencedSubgroups)

    }




    static String myKeysOnlySqlString(ComplyProcess complyProcess, String restrictUser) {
        String sqlString = """
select
    ena_versions_creation.collection_key as "collection_key"
     from eln_collections eln_collections
inner join eln_states on eln_states.primary_key=eln_collections.state_key
inner join eln_collection_types on eln_collection_types.section_set_key =eln_collections.collection_type_key
inner join eln_people on eln_people.primary_key =eln_collections.owner_key
inner join eln_collections eln_collections2 on eln_collections2.section_set_key=eln_people.home_collection_key
inner join eln_references on eln_references.primary_key=eln_collections2.container_key
inner join eln_collections eln_collections3 on eln_collections3.section_set_key=eln_references.collection_key
inner join eln_references eln_references2 on eln_references2.primary_key=eln_collections3.container_key
inner join eln_collections eln_collections4 on eln_collections4.section_set_key=eln_references2.collection_key
inner join ena_versions ena_versions_creation on ena_versions_creation.primary_key=eln_collections.creation_version
inner join ena_versions ena_versions_lastmod  on ena_versions_lastmod.primary_key=eln_collections.last_modified_version
where
    (ena_versions_creation.action_date> to_date('""" + complyProcess.earliestDateOfInterest + """', 'yyyy/mm/dd:hh:mi:ssam'))
    """+restrictUser+"""
    and (eln_collection_types.plural_name = 'Chemistry Experiments')
    and (eln_collection_types.is_active = 1)
    """ + produceAndOrString("eln_states.name", complyProcess.statesOfInterest) + """
    """ + produceAndOrString("eln_collections3.name", complyProcess.referencedSubgroups)

    }


    static String myWitnessDescriptionsSqlString(ComplyProcess complyProcess,String collectionKey, String restrictUser) {
        String sqlString = """
select eln_collections.name as "Experiment_name"
       ,orig_sign.username as "Original_signer"
       ,ena_versions_signed.action_date as "Signing_date"
       ,witness.username as "Witness"
       ,round(sysdate - TO_DATE (TO_CHAR (ena_versions_signed.action_date, 'YYYY-MON-DD HH24:MI:SS'),
                'YYYY-MON-DD HH24:MI:SS'
                 ),1) as "days_since_signing" from ena_versions ena_versions
inner join ena_versions ena_versions_signed  on ena_versions_signed.collection_key=ena_versions.collection_key
inner join ena_versions ena_versions_assign_wit  on ena_versions_assign_wit.collection_key=ena_versions.collection_key
inner join eln_people orig_sign on orig_sign.primary_key =ena_versions.owner_key
inner join eln_people witness on witness.primary_key =ena_versions_assign_wit.owner_key
inner join eln_collections on eln_collections.last_modified_version=ena_versions.primary_key
where ena_versions.collection_key="""+collectionKey+""" and
      ena_versions.primary_key=(select max(primary_key) from ena_versions
                                where (annotation_required=0) and
                                collection_key="""+collectionKey+""") and
       ena_versions.transition_type_key is null and
      ena_versions_signed.annotation like 'Signing%' and
      ena_versions_assign_wit.owner_key!=ena_versions.owner_key
      and (ena_versions_signed.action_date < (sysdate- INTERVAL '""" + complyProcess.daysAllowedForSigning.toString() + """ 00:00:00.0' day to second(1)) )
      order by eln_collections.name,orig_sign.username
"""
    }


    static String generateContractSqlQuery(ComplyProcess complyProcess) {
        String sqlString = """
           select
                eln_collections2.name  as "full_name"
                ,eln_people.username  as "username"
           from eln_collections eln_collections
               inner join eln_references eln_references on eln_references.collection_key=eln_collections.section_set_key
               inner join eln_collections eln_collections2 on eln_collections2.container_key=eln_references.primary_key
               inner join eln_people on eln_people.primary_key =eln_collections2.owner_key
           where
               eln_people.email_address is not null
               and eln_people.primary_key not in (
                    select ena_versions.owner_key
                    from ena_versions ena_versions
                    inner join eln_collections eln_collections3 on  eln_collections3.section_set_key=ena_versions.collection_key
                    where eln_collections3.name = 'Verify ESignatureTraining'
               )  and eln_collections2.is_active=1
    """ + produceAndOrString("eln_collections.name", complyProcess.referencedSubgroups)
    }





}












/***
 * Here is the service
 */
class ElnOracleCheckerService {
    def dataSourceOracle
    boolean transactional = true


    List <HoldUsersAndRelatedInfo> getListOfUsers() {
        def holdUsersAndRelatedInfoList = new ArrayList<HoldUsersAndRelatedInfo>()
        if (dataSourceOracle) {
            def db= Sql.newInstance(dataSourceOracle)
            db.eachRow(ElnDatabaseHitter.sqlToRetrieveTheUsers()  ){  res->
                holdUsersAndRelatedInfoList << new  HoldUsersAndRelatedInfo (elnUsername:res.username,nameOfHuman:res.nameOfHuman,nameOfGroup:res.nameOfGroup,emailAddress:res.emailAddress)
            }
        }
        holdUsersAndRelatedInfoList
    }



    def getData(PersonalComplyProcess personalComplyProcess, int daysInTimeInterval, int daysUntilWarningTakesPlace) {
        StringBuffer stringBuffer = new StringBuffer ()
        if (dataSourceOracle) {
            def db= Sql.newInstance(dataSourceOracle)
            String sqlStr =   ElnDatabaseHitter.mySqlString(personalComplyProcess, daysInTimeInterval, daysUntilWarningTakesPlace)
            // To debug
         //   println("sqlStr = ${sqlStr}")
            db.eachRow(sqlStr){  res->
                stringBuffer << "${res.Experiment_name}\t${res.state}\t${res.username}\t${res.full_name}\t${res.days_since_creation}\n"
            }
            stringBuffer.toString()
        }
    }

    def getData(ComplyProcess complyProcess) {
        StringBuffer stringBuffer = new StringBuffer ()
        if (dataSourceOracle) {
            def db= Sql.newInstance(dataSourceOracle)
            db.eachRow(ElnDatabaseHitter.mySqlString(complyProcess,"")){  res->
                stringBuffer << "${res.Experiment_name}\t${res.state}\t${res.username}\t${res.full_name}\t${res.days_since_creation}\n"
            }
            stringBuffer.toString()
        }
    }


    String retrieveWitnessingInformation(ComplyProcess complyProcess,String relevantCollectionKeys) {
        StringBuffer stringBuffer = new StringBuffer()
        if (dataSourceOracle) {
            def db = Sql.newInstance(dataSourceOracle)
            relevantCollectionKeys.eachLine { collectionKey ->
                String sqlStr=ElnDatabaseHitter.myWitnessDescriptionsSqlString(complyProcess, collectionKey, "")
                //To debug
                //println "witness sql='${sqlStr}'"
                String lastExperimentName = ""  // If experiments are rejected multiple times we may have multiple records, however
                String lastOriginalSigner = ""  //  it doesn't help anybody to show these multiple records.  Therefore delete everyone after the first
                db.eachRow(sqlStr) {  res ->
                    if (res) {
                       if ((!(lastExperimentName.equals(res.Experiment_name))  && (! (lastOriginalSigner.equals(res.Original_signer)) )))
                          stringBuffer << "${res.Experiment_name}\t${res.Original_signer}\t${res.Witness}\t${res.days_since_signing}\n"
                        lastExperimentName = res.Experiment_name
                        lastOriginalSigner = res.Original_signer
                    }  else    {
                    }
                 }
            }
            stringBuffer.toString()
        }
    }

    /**
     * Return only the collection keys, which are then used in a subsequent query
     * @param complyProcess
     * @return
     */
    def getWitnessData(ComplyProcess complyProcess) {
        StringBuffer stringBuffer = new StringBuffer ()
        if (dataSourceOracle) {
            def db= Sql.newInstance(dataSourceOracle)
            db.eachRow(ElnDatabaseHitter.myKeysOnlySqlString(complyProcess,"")){  res->
                stringBuffer << "${res.collection_key}\n"
            }
            stringBuffer.toString()
        }
    }




    def getUsersWithoutAContract(ComplyProcess complyProcess) {
        StringBuffer stringBuffer = new StringBuffer ()
        if (dataSourceOracle) {
            def db= Sql.newInstance(dataSourceOracle)
            db.eachRow(ElnDatabaseHitter.generateContractSqlQuery(complyProcess)){  res->
                if (! ("mfoley".equals(res.username)))
                   stringBuffer << "${res.full_name}\t${res.username}\n"
            }
            stringBuffer.toString()
        }
    }




    java.util.LinkedHashMap executeAgainstDatabase(String sqlCommand) {
        def mapToBeReturned = new java.util.LinkedHashMap ()
        def db= Sql.newInstance(dataSourceOracle)
        db.eachRow(sqlCommand){  res->
            mapToBeReturned[res.username]=res.email_address
        }
        mapToBeReturned
    }

}

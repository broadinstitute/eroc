package org.broadinstitute.eln

import grails.plugin.mail.MailService

/**
 * Created by IntelliJ IDEA.
 * User: balexand
 * Date: 2/29/12
 * Time: 6:53 AM
 * To change this template use File | Settings | File Templates.
 */
class CommonLibraryRoutines {
    MailService mailService

    static  CommonLibraryRoutines instance

    /**
     * Here is the static method that allows access to the Singleton
     * @return
     */
    static synchronized CommonLibraryRoutines getInstance() {
        if (!instance) {
            instance = new CommonLibraryRoutines()
        }
        instance
    }

    void writeToArchiveFile(String mailAddress,
                                    String emailSubjectLine,
                                    String textForEmail,
                                    String pathForArchiveFile) {
        def namePortionOfEmailAddress = mailAddress.split("@")[0]
        def archiveFile = new File(pathForArchiveFile + "/" + namePortionOfEmailAddress + ".txt")
        archiveFile << "To: ${mailAddress}\n".toString()
        archiveFile << "Subject: ${emailSubjectLine}\n".toString()
        archiveFile << "${textForEmail}".toString()
    }


    String provideADatedSubdirectory(String rootPath) {
        // a null signifier root path doesn't make much sense, but I suppose it should be legal
        if (!rootPath) rootPath = ""
        String returnValue
        String proposedDirectoryName = new Date().format('yyyyMM')
        String  proposedDirectoryPlusRoot  =   rootPath + File.separator + proposedDirectoryName
        File rootPathAsFile = new File(proposedDirectoryPlusRoot)
        if (rootPathAsFile.exists())
            returnValue =  rootPathAsFile.toString()
        else {
            if (!rootPathAsFile.mkdirs()) {
                // we would prefer to have the root path in place, but try to recover if it's not there
                reportSeriousErrorCondition("Problem creating = ${proposedDirectoryPlusRoot}.")
                returnValue = "/"
            }  else
                returnValue =  rootPathAsFile.toString()
        }
        returnValue
    }

    String provideADailySubdirectory(String rootPath) {
        // a null signifier root path doesn't make much sense, but I suppose it should be legal
        if (!rootPath) rootPath = ""
        String returnValue
        String proposedDirectoryName = new Date().format('dd')
        String  proposedDirectoryPlusRoot  =   rootPath + File.separator + proposedDirectoryName
        File rootPathAsFile = new File(proposedDirectoryPlusRoot)
        if (rootPathAsFile.exists())
            returnValue =  rootPathAsFile.toString()
        else {
            if (!rootPathAsFile.mkdirs()) {
                // we would prefer to have the root path in place, but try to recover if it's not there
                reportSeriousErrorCondition("Problem creating = ${proposedDirectoryPlusRoot}.")
                returnValue = "/"
            }  else
                returnValue =  rootPathAsFile.toString()
        }
        returnValue
    }



    void reportSeriousErrorCondition(String errorText) {
        println  "Now sending e-mail to ${ApplicationSharedValues.getInstance().erocAdministratorEmailAddress} with=${errorText}"
        mailService.sendMail {
            to ApplicationSharedValues.getInstance().erocAdministratorEmailAddress
            subject "ELN Compliance Checker error!"
            body "${errorText}"
        }
    }
//

    def doWorkForIndividualReports(MailService mailService,
                                   ElnOracleCheckerService elnOracleCheckerService) {
        ApplicationSharedValues applicationSharedValues = ApplicationSharedValues.getInstance()
        def emailSubjectLine = "Experiment signing reminder"
        boolean pilotModeOnly = PersonalComplyProcess.pilotModeUsers.size() > 0
        int loopIndex = 1
        println "Ready to consider the individuals"
        List<HoldUsersAndRelatedInfo> holdUsersAndRelatedInfoList = elnOracleCheckerService.getListOfUsers()
        if (holdUsersAndRelatedInfoList.size() <= 1)
            println "There were no individuals suitable for inclusion"
        else {
            holdUsersAndRelatedInfoList.each { HoldUsersAndRelatedInfo holdUsersAndRelatedInfo ->
                PersonalComplyProcess personalComplyProcess = new PersonalComplyProcess(individual: holdUsersAndRelatedInfo.elnUsername,
                        name: "Program, signing",
                        mailRecipients: ["${holdUsersAndRelatedInfo.emailAddress}".toString()],
                        mailSubjectLine: "Experiment signing Reminder",
                        statesOfInterest: ["Open", "Re-Opened"],
                        referencedSubgroups: ["${holdUsersAndRelatedInfo.nameOfGroup}".toString()],
                        earliestDateOfInterest: "2012/02/01:12:00:00AM",
                        daysAllowedForSigning: applicationSharedValues.daysBeforeIndividualWarning)
                if (personalComplyProcess.mailRecipients) {
                    if ((!pilotModeOnly) ||
                            (pilotModeOnly &&
                                    (PersonalComplyProcess.pilotModeUsers.contains(personalComplyProcess.individual)))) {
                        String response = elnOracleCheckerService.getData(personalComplyProcess,
                                applicationSharedValues.timeIntervalOverWhichUsersAreNotified,
                                applicationSharedValues.daysBeforeIndividualWarning)
                        // Decide what to do based on the number of overdue experiments
                        //   0 -> no e-mail
                        //   1 -> singular e-mail
                        //   2 -> plural e-mail
                        if (response?.length() > 1) {

                            String[] lateExperiments = response.split('\n')
                            int numberOfExperiments = lateExperiments.size()
                            String textForEmail
                            if (numberOfExperiments == 1) {
                                String[] elements = lateExperiments[0].split('\t')
                                String experimentName
                                String approximateAgeInDays
                                if (elements?.size() == 5) {
                                    experimentName = elements[0]
                                    approximateAgeInDays = elements[4]
                                } else {
                                    CommonLibraryRoutines.getInstance().reportSeriousErrorCondition("Expected 5 tabs in ${elements}. What is going on here!?")
                                    return
                                }
                                textForEmail = "Dear ${holdUsersAndRelatedInfo.nameOfHuman};\n\n" +
                                        "The email is sent to alert you to the fact that the experiment\n\n" +
                                        "\"" + experimentName + "\"\n\n" +
                                        "Is roughly ${approximateAgeInDays} days old.  Please apply an electronic signature to this experiment at your earliest convenience."
                            } else {
                                def stringBuffer = new StringBuffer("Dear ${holdUsersAndRelatedInfo.nameOfHuman};\n\n" +
                                        "The email is sent to remind you of some experiments that need your eSignature.\n\n" +
                                        "Experiment name\t\tAge (in days)\n");
                                lateExperiments.each { String oneLine ->
                                    String[] elements = oneLine.split('\t')
                                    String experimentName = (elements?.size() == 5) ? (elements[0] + '\t' + '\t' + elements[4]) : oneLine
                                    stringBuffer << (experimentName + '\n')
                                }
                                stringBuffer << "\n\nPlease apply an electronic signature to each of these experiments at your earliest convenience."
                                textForEmail = stringBuffer.toString()
                            }
                            if (applicationSharedValues.sendEmailToIndividuals) {
                                println "Ready to send e-mail to ${holdUsersAndRelatedInfo.emailAddress}"
                                mailService.sendMail {
                                    to holdUsersAndRelatedInfo.emailAddress //"${listOfUsersToEmail.toString()}"
                                    subject emailSubjectLine
                                    body "${textForEmail}"
                                }
                            }
                            if (applicationSharedValues.writeArchiveFilesForIndividuals) {
                                CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
                                commonLibraryRoutines.writeToArchiveFile(holdUsersAndRelatedInfo.emailAddress,
                                        emailSubjectLine,
                                        textForEmail,
                                        commonLibraryRoutines.provideADailySubdirectory(commonLibraryRoutines.provideADatedSubdirectory(applicationSharedValues.pathwayToStoreErocForIndividuals)))
                            }
                        }
                    }
                }
            }
        }
    }



    def doWorkForManagersReports( MailService mailService,
                                  ElnOracleCheckerService elnOracleCheckerService) {
        // Check our listed jobs, and execute each one
        List<ComplyProcess> listForFiltering = ComplyProcess.findAll()
        List<ComplyProcess> listComplyProcess = listForFiltering.findAll{"ComplyProcess".equals(it.class.simpleName)}
        Integer numberOfJobs = listComplyProcess.size()
        if (numberOfJobs) {
            boolean pilotModeOnly = PersonalComplyProcess.pilotModeUsers.size() > 0
            int loopIndex = 1
            listComplyProcess.each {complyProcess ->

                println "Manager compliance check: job ${loopIndex++} of ${numberOfJobs},  '${complyProcess.name}'"

                if (complyProcess.mailRecipients) {
                    if ((!pilotModeOnly) ||
                            (pilotModeOnly &&
                                    (ComplyProcess.pilotModeUsers.contains(complyProcess.individual)))) {
                        String response = elnOracleCheckerService.getData(complyProcess)
                        if ((!response) || (response.length() < 2))
                            response = "\n\nNo users were beyond the ${complyProcess.daysAllowedForSigning} day limit!\n\n\n"
                        else
                            response = "The following users were beyond the ${complyProcess.daysAllowedForSigning} day signing limit.\n\n" +
                                    "Experiment name\tExperiment state\tUsername\tFull name\tDays since creation without a signature\n${response}\n\n\n"
                        String usersWithNoContract = elnOracleCheckerService.getUsersWithoutAContract(complyProcess)
                        if (usersWithNoContract?.length() < 2)
                            response += """All members of your group have signed their 'ESignature Contract'!\n\n"""
                        else
                        response += """In addition to signing experiments, we need to have users sign their 'ESignature Contract'.  This is a document
(located under 'Departmental Links', and appearing with the title "Verify ESignature Training") which everyone
should open, read, and then electronically sign. People only need to sign it once, but it is important that
everyone make the effort. The following persons in your group still need to complete this step:""" + "\n\n" + usersWithNoContract
                        if (ApplicationSharedValues.getInstance().sendEmailToManagers) {
                            println "Ready to send e-mail to ${complyProcess.mailRecipients}"
                            mailService.sendMail {
                                to complyProcess.mailRecipients //"${listOfUsersToEmail.toString()}"
                                subject "${complyProcess.mailSubjectLine}"
                                body "${response}"
                            }
                        }
                        if (ApplicationSharedValues.getInstance().writeArchiveFilesForManagers) {
                            String archiveName =   complyProcess.mailRecipients[0]
                            CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
                            commonLibraryRoutines.writeToArchiveFile(archiveName,
                                    complyProcess.mailSubjectLine,
                                    response,
                                    commonLibraryRoutines.provideADailySubdirectory(commonLibraryRoutines.provideADatedSubdirectory(ApplicationSharedValues.getInstance().pathwayToStoreErocForManagers)))
                        }


                    }
                }
            }

        }

    }




    def doWorkForManagersWitnessReports( MailService mailService,
                                         ElnOracleCheckerService elnOracleCheckerService) {
        // Check our listed jobs, and execute each one
        List<WitnessComplyProcess> listWitnessComplyProcess = WitnessComplyProcess.findAll()
        Integer numberOfJobs = listWitnessComplyProcess.size()
        if (numberOfJobs) {
            boolean pilotModeOnly = PersonalComplyProcess.pilotModeUsers.size() > 0
            int loopIndex = 1
            listWitnessComplyProcess.each {witnessComplyProcess ->

                println "Manager compliance witness check: job ${loopIndex++} of ${numberOfJobs},  '${witnessComplyProcess.name}'"

                if (witnessComplyProcess.mailRecipients) {
                    if ((!pilotModeOnly) ||
                            (pilotModeOnly &&
                                    (ComplyProcess.pilotModeUsers.contains(witnessComplyProcess.individual)))) {
                        String response = elnOracleCheckerService.getWitnessData(witnessComplyProcess)
                        if ((!response) || (response.length() < 2))
                            response = "\n\nNo users were beyond the ${witnessComplyProcess.daysAllowedForCounterSigning} day limit!\n\n\n"
                        else  {
                            String  witnessingData =  elnOracleCheckerService.retrieveWitnessingInformation(witnessComplyProcess,response)
                            if (witnessingData.length() > 2 )
                            response = "The following users were beyond the ${witnessComplyProcess.daysAllowedForCounterSigning} day witnessing limit.\n" +
                                    "(Note: The experiment's 'Original signer' is included below for reference, but it is instead the\n" +
                                    "'Proposed witness' who needs to catch up with their signing duties.)\n\n" +
                                    "Experiment name\tOriginal signer\tProposed witness\tDays since signing\n${witnessingData}\n\n\n"
                            else
                                response = "\n\nNo users were beyond the ${witnessComplyProcess.daysAllowedForCounterSigning} day limit!\n\n\n"
                        }
                        if (ApplicationSharedValues.getInstance().sendEmailToManagers) {
                            println "Ready to send e-mail to ${witnessComplyProcess.mailRecipients}"
                            mailService.sendMail {
                                to witnessComplyProcess.mailRecipients //"${listOfUsersToEmail.toString()}"
                                subject "${witnessComplyProcess.mailSubjectLine}"
                                body "${response}"
                            }
                        }
                        if (ApplicationSharedValues.getInstance().writeArchiveFilesForManagers) {
                            String archiveName =   witnessComplyProcess.mailRecipients[0]
                            CommonLibraryRoutines commonLibraryRoutines = CommonLibraryRoutines.getInstance()
                            commonLibraryRoutines.writeToArchiveFile(archiveName,
                                    witnessComplyProcess.mailSubjectLine,
                                    response,
                                    commonLibraryRoutines.provideADailySubdirectory(commonLibraryRoutines.provideADatedSubdirectory(ApplicationSharedValues.getInstance().pathwayToStoreErocForManagers)))
                        }


                    }
                }
            }

        }

    }





}



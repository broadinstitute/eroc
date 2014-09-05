package org.broadinstitute.eln

import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.mail.MailService

class ApplicationSharedValuesController {
    MailService mailService
    ElnOracleCheckerService elnOracleCheckerService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "show", params: params)
    }

    def runIndividualReports() {
        println("i would have sent out individual reports now")
        CommonLibraryRoutines.getInstance().doWorkForIndividualReports ( mailService,
                                                        elnOracleCheckerService )
        redirect(action: "show", params: params)
    }

    def list() {
        redirect(action: "show", params: params)
    }

    def create() {
        redirect(action: "show", params: params)
    }

    def save() {
        def applicationSharedValuesInstance = new ApplicationSharedValues(params)
        if (!applicationSharedValuesInstance.save(flush: true)) {
            render(view: "create", model: [applicationSharedValuesInstance: applicationSharedValuesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues'), applicationSharedValuesInstance.id])
        redirect(action: "show", id: 1)
    }

    def show() {
        def applicationSharedValuesInstance = ApplicationSharedValues.getInstance()
        if (!applicationSharedValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues'), params.id])
            redirect(action: "list")
            return
        }

        [applicationSharedValuesInstance: applicationSharedValuesInstance]
    }

    def edit() {
        def applicationSharedValuesInstance = ApplicationSharedValues.getInstance()
        if (!applicationSharedValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues'), params.id])
            redirect(action: "list")
            return
        }

        [applicationSharedValuesInstance: applicationSharedValuesInstance]
    }

    def update() {
        def applicationSharedValuesInstance =  ApplicationSharedValues.getInstance()
        if (!applicationSharedValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (applicationSharedValuesInstance.version > version) {
                applicationSharedValuesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues')] as Object[],
                        "Another user has updated this ApplicationSharedValues while you were editing")
                render(view: "edit", model: [applicationSharedValuesInstance: applicationSharedValuesInstance])
                return
            }
        }

        applicationSharedValuesInstance.properties = params

        if (!applicationSharedValuesInstance.save(flush: true)) {
            render(view: "edit", model: [applicationSharedValuesInstance: applicationSharedValuesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues'), applicationSharedValuesInstance.id])
        redirect(action: "show", id: applicationSharedValuesInstance.id)
    }

    def delete() {
        flash.message = "You can't delete a singleton!"
        redirect(action: "show", id: 1)
    }
}

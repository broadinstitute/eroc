package org.broadinstitute.eln

import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.mail.MailService

class ComplyProcessController {
    MailService mailService
    ElnOracleCheckerService elnOracleCheckerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def runManagerReports() {
        println("i would have sent out manager reports now")
        CommonLibraryRoutines.getInstance().doWorkForManagersReports ( mailService,
                elnOracleCheckerService )
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [complyProcessInstanceList:  ComplyProcess.list(params).findAll(){!"PersonalComplyProcess".equals(it.class.simpleName)}, complyProcessInstanceTotal: ComplyProcess.count()]
    }

    def create() {
        [complyProcessInstance: new ComplyProcess(params)]
    }

    def save() {
        def complyProcessInstance = new ComplyProcess(params)
        if (!complyProcessInstance.save(flush: true)) {
            render(view: "create", model: [complyProcessInstance: complyProcessInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), complyProcessInstance.id])
        redirect(action: "show", id: complyProcessInstance.id)
    }

    def show() {
        def complyProcessInstance = ComplyProcess.get(params.id)
        if (!complyProcessInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "list")
            return
        }

        [complyProcessInstance: complyProcessInstance]
    }

    def edit() {
        def complyProcessInstance = ComplyProcess.get(params.id)
        if (!complyProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "list")
            return
        }

        [complyProcessInstance: complyProcessInstance]
    }

    def update() {
        def complyProcessInstance = ComplyProcess.get(params.id)
        if (!complyProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (complyProcessInstance.version > version) {
                complyProcessInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'complyProcess.label', default: 'ComplyProcess')] as Object[],
                          "Another user has updated this ComplyProcess while you were editing")
                render(view: "edit", model: [complyProcessInstance: complyProcessInstance])
                return
            }
        }

        complyProcessInstance.properties = params

        if (!complyProcessInstance.save(flush: true)) {
            render(view: "edit", model: [complyProcessInstance: complyProcessInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), complyProcessInstance.id])
        redirect(action: "show", id: complyProcessInstance.id)
    }

    def delete() {
        def complyProcessInstance = ComplyProcess.get(params.id)
        if (!complyProcessInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "list")
            return
        }

        try {
            complyProcessInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'complyProcess.label', default: 'ComplyProcess'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

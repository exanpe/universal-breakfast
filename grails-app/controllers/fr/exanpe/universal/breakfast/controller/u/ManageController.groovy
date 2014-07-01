package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class ManageController {

    def ubService
    def springSecurityService

    def index() {
        def team = Team.get(springSecurityService.currentUser.id)
        def members = Member.getListOrdered(team).list()
        return [total: members.size, members : members]
    }

    def show(Member memberInstance) {
        respond memberInstance
    }

    def create() {
        respond new Member(params)
    }

    @Transactional
    def save() {
        def memberInstance = new Member(params)
        if (memberInstance == null) {
            notFound()
            redirect(action: "index")
            return
        }

        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view:'create'
            return
        }

        Member m = memberInstance.save flush:true
        def team = Team.get(springSecurityService.currentUser.id)
        team.addToMembers(m)
        team.save(flush: true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect(action: "index")
            }
            '*' { respond memberInstance, [status: CREATED] }
        }
    }

    def edit() {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = message(code: 'ub.manage.member.not.found.message', args: [params.id])
            redirect(action: "index")
            return
        }
        [memberInstance: memberInstance]
    }

    @Transactional
    def update() {
        def memberInstance = Member.get(params.id)

        if (memberInstance == null) {
            notFound()
            return
        }

        memberInstance.properties = params
        memberInstance.validate()
        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view:'edit'
            return
        }

        memberInstance.save(flush: true)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect(action: "show", id: memberInstance.id)
            }
            '*'{ respond memberInstance, [status: OK] }
        }
    }

    @Transactional
    def delete() {
        def memberInstance = Member.get(params.id)
        if (memberInstance == null) {
            notFound()
            return
        }

        memberInstance.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
        redirect(action: "index")
        return
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'Member.label', default: 'Member'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

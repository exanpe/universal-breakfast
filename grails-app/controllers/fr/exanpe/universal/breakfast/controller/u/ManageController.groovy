package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class ManageController {

    def ubService
    def springSecurityService
    def memberService

    def index() {
        def team = Team.get(springSecurityService.currentUser.id)
        def members = Member.getListOrdered(team).list()
        return [total: members.size, members : members]
    }

    def show(Member memberInstance) {
        if (!memberInstance) {
            flash.message = message(code: 'ub.manage.member.not.found.message', args: [params.id])
            redirect(action: "index")
            return
        }

        if (!memberService.checkTeam(memberInstance)) {
            kickYourAss()
            return
        }
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

        memberService.save(memberInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [memberInstance.name])
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

        if (!memberService.checkTeam(memberInstance)) {
            kickYourAss()
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

        if (!memberService.checkTeam(memberInstance)) {
            kickYourAss()
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
                flash.message = message(code: 'default.updated.message', args: [memberInstance.name])
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

        boolean success = memberService.deleteMember(memberInstance)
        if (!success) {
            kickYourAss()
            return
        }

        flash.message = message(code: 'default.deleted.message', args: [memberInstance.name])
        redirect(action: "index")
        return
    }

    @Transactional
    def toggle() {
        def memberInstance = Member.get(params.id)
        if (memberInstance == null) {
            notFound()
            return
        }

        boolean success = memberService.toggle(memberInstance)
        if (!success) {
            kickYourAss()
            return
        }

        String state = memberInstance.active ? message(code: 'ub.member.active') : message(code: 'ub.member.inactive')
        flash.message = message(code: 'default.toggle.message', args: [memberInstance.name, state])
        redirect(action: "index")
        return
    }

    protected void kickYourAss() {
        SecurityContextHolder.clearContext()
        flash.message = message(code: "ub.logout.member.team.security.message")
        redirect controller: 'home', action: 'index'
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

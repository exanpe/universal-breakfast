import fr.exanpe.universal.breakfast.domain.*
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        // Create ROLE_USER role if not exist
        def roleUser = addRole('ROLE_USER');

        if (Environment.current == Environment.DEVELOPMENT) {
            def team = new Team(username: 'team', password: 'te.am', mail: 'team@universal-breakfast.com', enabled : true).save(flush: true)
            TeamRole.create(team, roleUser, true)

            def mem = new Member(name : "Andrew", mail: "andrew@universal-breakfast.com")
            def mem2 = new Member(name : "Brian", mail: "brian@universal-breakfast.com")

            team.members << mem
            team.members << mem2

            team.save(flush : true)

            for(i in 1..43)
                new History(team: team, suppliers: "Andrew", date: new Date().minus(i*2), numAttendees: new Random().nextInt(i)).save(flush:true)


            def newteam = new Team(username: 'newteam', password: 'te.am', mail: 'newteam@universal-breakfast.com').save(flush: true)
            TeamRole.create(newteam, roleUser, true)
        }
    }

    def destroy = {
    }

    private Role addRole(String role) {
        Role.findByAuthority(role) ?: new Role(authority: role).save(flush: true)
    }
}
package fr.exanpe.universal.breakfast.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Team)
class TeamSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation"() {
        expect :
            new Team(map).validate() == res
        where :

        map                     |   res
        [:]                      |   false
        [username:"user"]       |   false
        [username:"user", password : "password"]       |   false
        [username:"user", password : "password", mail: "test@mail.com"]       |   true

    }
}

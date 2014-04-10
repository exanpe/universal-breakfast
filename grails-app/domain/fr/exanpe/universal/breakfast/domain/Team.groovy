package fr.exanpe.universal.breakfast.domain

class Team {

    Integer id

    String username
    String password

    Integer breakfastCount = 0

    Date dateCreation
    Date lastForewarn

    static constraints = {
        username blank: false, nullable: false, unique: true, maxSize: 32
        password blank: false, nullable: false
        lastForewarn nullable: true
        dateCreation nullable: true
    }

    static namedQueries = {
        findByUsername { name ->
            ilike 'username' , name
        }

        findByUsernameCase { name ->
            eq 'username' , name, ignoreCase : true
        }
    }

    def beforeInsert = {
        dateCreation = new Date()
    }

}

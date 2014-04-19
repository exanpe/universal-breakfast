package fr.exanpe.universal.breakfast.domain

class Team {

    String username
    String password
    // mail to contact team
    String mail;

    List<Member> members = new ArrayList<Member>()

    static hasMany = [members: Member]

    Integer breakfastCount = 0

    Date dateCreation
    Date lastPreparation
    Date lastValidation

    static mapping = {
        members cascade: 'all'
    }

    static constraints = {
        username blank: false, nullable: false, unique: true, maxSize: 32
        password blank: false, nullable: false
        mail blank: false, nullable: false, maxSize: 64
    }

    static namedQueries = {
        findByUsernameCI { name ->
            eq 'username' , name, ignoreCase : true
        }
    }

    def beforeInsert = {
        dateCreation = new Date()
    }

}

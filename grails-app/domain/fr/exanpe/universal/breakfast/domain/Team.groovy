package fr.exanpe.universal.breakfast.domain

class Team {

    String username
    String password
    // mail to contact team
    String mail;
    boolean enabled = false
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    List<Member> members = new ArrayList<Member>()
    List<Member> history = new ArrayList<History>()
    Configuration configuration
    Integer breakfastCount = 0
    Date lastPreparation
    Date lastValidation

    //standard grails
    Date dateCreated
    Date lastUpdated

    transient springSecurityService

    public Team(){
        configuration = new Configuration();
    }

    static hasMany = [
        members: Member,
        history: History
    ]

    static fetchMode = [configuration: 'eager']

    static mapping = {
        members cascade: 'all-delete-orphan'
        history cascade : 'all-delete-orphan'
        configuration cascade : 'all-delete-orphan'
        password column: '`password`'
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

    Set<Role> getAuthorities() {
        TeamRole.findAllByTeam(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}

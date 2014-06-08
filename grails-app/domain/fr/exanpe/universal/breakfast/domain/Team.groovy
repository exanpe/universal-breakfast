package fr.exanpe.universal.breakfast.domain

class Team {

    String username
    String teamName
    String password
    // mail to contact team
    String mail;
    boolean enabled = false
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    List<Member> members = new ArrayList<Member>()
    List<Member> history = new ArrayList<History>()
    WorkflowState workflowState = WorkflowState.NEW
    Configuration configuration
    Date breakfastScheduledDate//date selected on "prepare" step

    Integer breakfastCount = 0
    Date lastPrepare
    Date lastComplete
    Date lastConnection

    //standard grails
    Date dateCreated
    Date lastUpdated

    transient springSecurityService

    public Team(){
        configuration = new Configuration()
    }

    static hasMany = [
        members: Member,
        history: History
    ]

    static mapping = {
        members cascade: 'all-delete-orphan'
        history cascade : 'all-delete-orphan'
        configuration cascade : 'all-delete-orphan'
        password column: '`password`'
        lastConnection type : 'timestamp'
        breakfastScheduledDate type : 'date'
        lastPrepare type : 'date'
        lastComplete type : 'date'
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
        teamName = username
        username = username?.toLowerCase()
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

enum WorkflowState {
    //new creation
    NEW,
    //prepare sent
    PREPARE,
    //gather done
    GATHER,
    //complete done
    COMPLETE
}
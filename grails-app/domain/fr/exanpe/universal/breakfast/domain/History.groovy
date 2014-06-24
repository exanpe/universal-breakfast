package fr.exanpe.universal.breakfast.domain

class History {

    Date date
    String suppliers
    Integer numAttendees

    static belongsTo = [team:Team]

    static constraints = {
        suppliers nullable: false
        numAttendees nullable: false
        date nullable: false
        team nullable: false
    }

    static mapping = {
        team index : 'idx_history_team'
    }

    def beforeInsert = {
        date.clearTime();
    }
}

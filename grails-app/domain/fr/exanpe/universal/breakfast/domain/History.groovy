package fr.exanpe.universal.breakfast.domain

class History {

    Date date
    String providers
    Integer numAttendees

    static belongsTo = [team:Team]

    static constraints = {
        providers nullable: false
        numAttendees nullable: false
        date nullable: false
        team nullable: false
    }

    def beforeInsert = {
        date.clearTime();
    }
}

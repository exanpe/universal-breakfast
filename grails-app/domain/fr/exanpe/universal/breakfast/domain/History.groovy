package fr.exanpe.universal.breakfast.domain

class History {

    Date date
    String memberName
    Integer attendees

    static belongsTo = [team:Team]

    static constraints = {
        attendees nullable: false
        memberName nullable: false
        date nullable: false
    }
}

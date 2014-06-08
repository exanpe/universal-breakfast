package fr.exanpe.universal.breakfast.domain

class Member {

    String name;
    String mail;

    //scale to compute who's next to bring
    Integer scaleValue = 0;

    Integer breakfastCount = 0
    Integer homeMadeCount = 0

    Integer attendingCount = 0;
    Integer absentCount = 0;

    Date dateLastBreakfast;

    Boolean active = true;

    //standard grails
    Date dateCreated
    Date lastUpdated

    Boolean preparing = false

    static belongsTo = [team: Team]

    static constraints = {
        name nullable: false, blank: false, maxSize: 64
        mail nullable: true, blank: false, maxSize: 64
    }

    static namedQueries = {
        getListOrderedActive{ team ->
            eq "team", team
            eq "active", true
            order "scaleValue", "asc"
            order "dateLastBreakfast", "asc"
        }
        getListOrdered{ team ->
            eq "team", team
            order "scaleValue", "asc"
            order "dateLastBreakfast", "asc"
        }
    }

}

package fr.exanpe.universal.breakfast.domain

class Member {

    String name;
    String mail;

    //scale to compute who's next to bring
    Integer scaleValue = 0;

    Integer breakfastCount = 0
    Integer attendingCount = 0;
    Integer absenceCount = 0;

    Date lastBreakfast;

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

    static mapping = {
        lastBreakfast type:'date'
    }

    static namedQueries = {
        getListOrderedActive{ team ->
            eq "team", team
            eq "active", true
            order "scaleValue", "asc"
            order "lastBreakfast", "asc"
            order "id", "asc"//first registered
        }
        getListOrdered{ team ->
            eq "team", team
            order "scaleValue", "asc"
            order "lastBreakfast", "asc"
            order "id", "asc"//first registered
        }
        findByTeamAndNameCI{ team, name ->
            eq "team", team
            eq 'name', name, ignoreCase: true
        }
        countActives{ team ->
            eq 'team', team
            eq 'active', true
            projections {
                count("id")
            }
        }
    }

}

package fr.exanpe.universal.breakfast.domain

class Member {

    Integer id;

    String name;
    String mail;

    //scale to compute who's next to bring
    Integer scaleValue = 0;

    Integer breakfastCount = 0
    Integer homeMadeCount = 0

    Integer attendingCount = 0;
    Integer absentCount = 0;

    Date dateLastBreakfast;
    Date dateCreation;

    static constraints = {
        name nullable: false, blank: false, maxSize: 64
        mail nullable: true, blank: false, maxSize: 64
    }


    def beforeInsert = {
        dateCreation = new Date()
    }

}

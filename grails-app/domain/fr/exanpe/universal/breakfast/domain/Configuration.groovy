package fr.exanpe.universal.breakfast.domain

class Configuration {

    Boolean sendMail = true

    //add more configuration

    static belongsTo = [team:Team]

}

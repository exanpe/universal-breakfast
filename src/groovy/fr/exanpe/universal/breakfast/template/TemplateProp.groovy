package fr.exanpe.universal.breakfast.template

/**
 * Created by jmaupoux on 04/06/14.
 */
class TemplateProp {

    String id

    /**
     * Key as filled in the template
     */
    String templateKey

    /**
     * Closure getting the script. receive the custom model as arg
     */
    Closure script

    def getDescriptionMsgCode(){

        return "ub.template."+id+".description"
    }
}

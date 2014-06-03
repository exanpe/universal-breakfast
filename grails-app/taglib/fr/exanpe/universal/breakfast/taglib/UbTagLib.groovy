package fr.exanpe.universal.breakfast.taglib

class UbTagLib {
    static namespace = "ub"

    static defaultEncodeAs = 'raw'
    //static encodeAsForTags = [tagName: 'raw']

    def required = { attrs, body ->
        out << render(template: "/tpl/required",
                model: [body: body()]
            )
    }
}

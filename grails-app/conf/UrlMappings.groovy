class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/planning/$teamName?(.$format)?"{
            controller = 'planning'
            constraints {
                teamName nullable: false, blank: false
            }
        }

        "/"(controller: "home")
        "500"(view:'/error')
	}
}

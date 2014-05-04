class UrlMappings {

	static mappings = {
        "/$namespace/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home")
        "500"(view:'/error')
	}
}

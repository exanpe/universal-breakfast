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
        "/card/$teamName/$memberName(.$format)?"{
            controller = 'card'
            action = 'member'
            constraints {
                teamName nullable: false, blank: false
            }
        }
        "/card/$teamName(.$format)?"{
            controller = 'card'
            action = 'team'
            constraints {
                teamName nullable: false, blank: false
            }
        }

        "/"(controller: "home")
        "500"(view:'/error')
	}
}

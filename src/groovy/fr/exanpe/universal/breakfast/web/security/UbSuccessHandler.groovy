package fr.exanpe.universal.breakfast.web.security

import fr.exanpe.universal.breakfast.service.UbService
import grails.plugin.springsecurity.web.authentication.AjaxAwareAuthenticationSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by jmaupoux on 01/06/14.
 */
class UbSuccessHandler extends AjaxAwareAuthenticationSuccessHandler{

    @Autowired
    private UbService ubService

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        ubService.onConnection();

        super.onAuthenticationSuccess(request, response, authentication)
    }
}

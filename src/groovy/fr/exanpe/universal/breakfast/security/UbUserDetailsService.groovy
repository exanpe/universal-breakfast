package fr.exanpe.universal.breakfast.security

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * Ub implementation of <code>GrailsUserDetailsService</code> that uses insensitive username.
 */
class UbUserDetailsService extends GormUserDetailsService {

    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        username = username?.toLowerCase()
        super.loadUserByUsername(username, loadRoles)
    }

}

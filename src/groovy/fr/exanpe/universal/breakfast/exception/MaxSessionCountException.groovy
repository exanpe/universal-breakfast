package fr.exanpe.universal.breakfast.exception

import org.springframework.security.authentication.AuthenticationServiceException

class MaxSessionCountException extends AuthenticationServiceException {

    MaxSessionCountException(String message) {
        super(message)
    }

}

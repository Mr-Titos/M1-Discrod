package fr.discrod.discrod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The user specified is already the friend of the user.")
public class FriendAlreadyInListException extends RuntimeException{
}

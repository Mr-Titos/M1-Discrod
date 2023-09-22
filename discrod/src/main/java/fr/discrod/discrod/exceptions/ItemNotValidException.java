package fr.discrod.discrod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "The item supplied isn't valid.")
public class ItemNotValidException extends RuntimeException{
}

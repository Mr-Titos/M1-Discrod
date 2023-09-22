package fr.discrod.discrod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The item supplied doesn't exist.")
public class ItemNotCompleteException extends RuntimeException{
}

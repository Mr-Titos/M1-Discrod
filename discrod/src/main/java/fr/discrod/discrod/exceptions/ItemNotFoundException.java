package fr.discrod.discrod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The item doesn't exist.")
public class ItemNotFoundException extends RuntimeException{}

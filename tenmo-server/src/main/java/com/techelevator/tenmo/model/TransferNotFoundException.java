package com.techelevator.tenmo.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND, reason = "Transfer Not Found.")
public class TransferNotFoundException extends RuntimeException{
}

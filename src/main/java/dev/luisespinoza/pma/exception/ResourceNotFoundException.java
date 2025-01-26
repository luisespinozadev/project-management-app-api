package dev.luisespinoza.pma.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mesage){
        super(mesage);
    }
}

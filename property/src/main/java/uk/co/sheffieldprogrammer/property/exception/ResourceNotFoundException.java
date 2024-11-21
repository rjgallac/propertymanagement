package uk.co.sheffieldprogrammer.property.exception;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String notFound) {
        super(notFound);
    }
}

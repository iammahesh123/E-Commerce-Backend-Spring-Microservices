package org.example.favouriteservice.exception;

public class FavouriteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FavouriteNotFoundException() {
        super();
    }

    public FavouriteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavouriteNotFoundException(String message) {
        super(message);
    }

    public FavouriteNotFoundException(Throwable cause) {
        super(cause);
    }



}

package com.gcu.conversationservice.data;

import java.util.Optional;

public interface DataAccessInterface<T>{

    public Optional<T> findByID(T t);

    /**
     * A method to create an object in the database
     * @param t The object to be persisted to the database
     * @return int The number of affected database rows
     */
    public T create(T t);

    /**
     * Removes an object from the database
     * @param t The object to be removed
     * @return int The number of affected database rows
     */
    public boolean delete(T t);
}

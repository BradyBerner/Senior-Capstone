package com.gcu.MessageService.data;

import java.util.List;

public interface DataAccessInterface<T>{

    public List<T> findAll(String id);

    /**
     * A method to create an object in the database
     * @param t The object to be persisted to the database
     * @return int The number of affected database rows
     */
    public T create(T t);

    public T update(T t);

    /**
     * Removes an object from the database
     * @param t The object to be removed
     * @return int The number of affected database rows
     */
    public boolean delete(T t);
}

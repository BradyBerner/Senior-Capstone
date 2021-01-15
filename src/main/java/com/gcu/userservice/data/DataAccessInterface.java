package com.gcu.userservice.data;

import java.util.List;
import java.util.Optional;

public interface DataAccessInterface<T>{

    /**
     * Returns "all" of a certain object from the database. In the case of large tables, the service that implements this method will determine at what point to stop returning data
     * @return A list of all of a certain object in the database
     */
    public List<T> findAll();

    /**
     * Returns a list of objects from the database with a certain ID, whether that is the object's primary key or a foreign key.
     * @param id The ID to search by
     * @return The list of all results with the searched ID.
     */
    public List<T> findAllWithID(String id);

    /**
     * Returns an object with a specific ID
     * @param id The id of the object for which the method is searching
     * @return T The object found with this ID, or a default object if none is found
     */
    public Optional<T> findByID(String id);

    /**
     * Returns an object back to the business service if that object is found in the database
     * @param t An object to be searched for in the database
     * @return T The same object if found, a default object if not found
     */
    public T findBy(T t);

    /**
     * Returns an object back to the business service if that object is found using a certain string
     * @param search A String search used to find a relevant object
     * @return T The object found with this search
     */
    public T findByString(String search);

    /**
     * A method to create an object in the database
     * @param t The object to be persisted to the database
     * @return int The number of affected database rows
     */
    public boolean create(T t);

    /**
     * A method to update a certain object in the database
     * @param t The updated object
     * @return int The number of affected database rows
     */
    public int update(T t);

    /**
     * Removes an object from the database
     * @param t The object to be removed
     * @return int The number of affected database rows
     */
    public int delete(T t);
}

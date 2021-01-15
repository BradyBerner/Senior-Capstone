package com.gcu.userservice.business;

import java.util.List;

public interface UserBusinessInterface<T> {

    /**
     * Method to add a user to the database
     * @param user A user to be registered
     * @return Returns a boolean stating success or failure of registration
     */
    public boolean register(T user);

    /**
     * Method that handles attempted user login
     * @param credentials A user model containing the credentials a user is attempting to log in with
     * @return The user's ID upon a success. -1 upon a failure
     */
    public T authenticate(T credentials);

    /**
     * Method to find a single user using that user's ID
     * @param id  The ID of the user being looked for
     * @return A found user, ID will be -1 if not found
     */
    public T findUserByID(String id);

    public List<T> findAll();
}

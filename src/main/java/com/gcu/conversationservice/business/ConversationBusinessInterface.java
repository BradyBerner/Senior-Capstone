package com.gcu.conversationservice.business;

import java.util.ArrayList;

public interface ConversationBusinessInterface<T> {

    public ArrayList<T> findAll();

    public T findByID(T conversation);

    public T create(T conversation);

    public boolean leave(T conversation);
}

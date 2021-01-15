package com.gcu.conversationservice.business;

public interface ConversationBusinessInterface<T> {

    public T findByID(T conversation);

    public T create(T conversation);

    public boolean leave(T conversation);
}

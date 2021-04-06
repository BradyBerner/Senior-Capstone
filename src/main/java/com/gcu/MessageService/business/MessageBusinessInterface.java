package com.gcu.MessageService.business;

import com.gcu.MessageService.model.ConversationModel;
import java.util.List;

public interface MessageBusinessInterface<T> {

    public List<T> getAll();

    public List<T> view(ConversationModel conversation);

    public T send(T message);

    public T edit(T message);

    public boolean delete(T message);
}

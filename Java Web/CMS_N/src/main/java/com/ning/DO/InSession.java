package com.ning.DO;

import org.hibernate.Session;

public interface InSession<T> {
    public T onQuery(Session session);
}

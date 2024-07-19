package com.turnero.redis;
public interface SessionDao {

    void saveSession(Session emp);

    Session getOneSession(String id);

    void updateSession(Session emp);

    void deleteSession(String id);

    boolean existeSession(String id);

}


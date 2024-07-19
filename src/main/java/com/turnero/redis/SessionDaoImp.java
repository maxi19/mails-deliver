package com.turnero.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turnero.controller.RecibosController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.*;


@Repository
public class SessionDaoImp implements SessionDao {


    private static final Logger logger =  LoggerFactory.getLogger(RecibosController.class);

    private final String hashReference= "Session";

    @Resource(name="redisTemplate")
    private HashOperations<String, String, Session> hashOperations;


    @Override
    public void saveSession(Session session) {
        try {
            hashOperations.putIfAbsent(session.getUsuario(), hashReference, session);
            logger.info("Se persiste en Redis los siguientes datos: " + session.toString());
        } catch (Throwable e) {
            logger.error("Error guardando datos en sesion: " + e.getMessage());
        }
    }


    @Override
    public boolean existeSession(String id) {
        return hashOperations.hasKey(id, hashReference);
    }


    @Override
    public Session getOneSession(String id) {
        try {
            Session session = parsearSession(id);
            logger.info("Se obtiene de Redis la session " + session.toString());
            return session;
        } catch (Throwable e) {
            logger.error("Error consultando los datos en sesion para el id: " + id + ". Detalle: " + e.getMessage());
            return null;
        }
    }

	private Session parsearSession(String id) throws JsonProcessingException {
    	Map<String , Session>  map = (Map<String, Session>) hashOperations.get(id, hashReference);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        Session session =  mapper.readValue(json, Session.class);
        return session;
    }


    @Override
    public void updateSession(Session session) {
        try {
            hashOperations.put(session.getUsuario(), hashReference, session);
            logger.info("Se actualiza la sesion en Redis con la siguiente data: " + session.toString());
        } catch (Throwable e) {
            logger.error("Error actualizando datos en sesion: " + e.getMessage());
        }
    }


    @Override
    public void deleteSession(String id) {
        try {
            hashOperations.delete(id, hashReference);
            logger.info("se elimina la session la session id :"+id);
        } catch (Throwable e) {
            logger.error("Error eliminando datos en sesion para el id: " + id + ". Detalle: " +  e.getMessage());
        }
    }

}

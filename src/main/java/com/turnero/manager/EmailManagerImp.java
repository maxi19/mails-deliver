package com.turnero.manager;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.component.MailService;
import com.turnero.dto.DestinatarioEntity;
import com.turnero.dto.Enviables;
import com.turnero.dto.Enviables.Destinatario;
import com.turnero.dto.Enviables.FileItem;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.redis.Session;
import com.turnero.redis.SessionDao;
import com.turnero.service.ReciboService;
import com.turnero.service.UserService;

@Service
public class EmailManagerImp implements EmailManager{

	@Autowired
	private UserService userService;
	
    @Autowired
    private MailService mailService;
    
    @Autowired
    private SessionDao sessionDao;
	
    private ReciboService reciboService;
    
	@Override
	public void enviarEmail(Enviables enviables, String userName) {
	
		User user = obtenerUsuario(userName);
		
		enviables.getDestinatarios().stream().forEach(destinatario ->{
				//refactorizar despues la obtencion de la configuracion de usuario smtp
				Session session = sessionDao.getOneSession(userName);
				destinatario.getFileItems().stream().forEach(item ->{
					  Optional<Recibo> opt =  buscarRecibo(item);
						mailService.enviarRecibos(new DestinatarioEntity(user, userName, userName, false, Arrays.asList(opt.get())));
				});
		});
	}



	private Optional<Recibo> buscarRecibo(FileItem item) {
		try {
		Optional<Recibo> recibo =	this.reciboService.buscarArchivosPorNombre(item.getName());
		return recibo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	private User obtenerUsuario(String userName) {
		try {
			return userService.findByUserName(userName);
		} catch (Exception e) {
			
		}
		return null;
	}

	
	
	
}

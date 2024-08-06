package com.turnero.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.turnero.component.MailService;
import com.turnero.controller.UsuariosController;
import com.turnero.dto.DestinatarioEntity;
import com.turnero.dto.Enviables;
import com.turnero.dto.Enviables.FileItem;
import com.turnero.dto.ItemEnviable;
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

    @Autowired
    private ReciboService reciboService;

	private static final Logger log =  LoggerFactory.getLogger(EmailManagerImp.class);

    
	@Override
	public void enviarEmail(Enviables enviables, String userName) throws Exception{

		User user = obtenerUsuario(userName);
		
		// nos fijamso si tiene mas de un adjujnto o uno solo
		enviables.getDestinatarios().stream().forEach(destinatario ->{
			Boolean esMultiple  = destinatario.getFileItems().size()>1? true : false;
			destinatario.setMultipleFile(esMultiple);
		});
				
		
		enviables.getDestinatarios().stream().forEach(destinatario ->{

					try {
						Optional<List<Recibo>> lstOptRecibos = this.reciboService.buscarPorEmailEnBandeja(destinatario.getEmail());
						List<ItemEnviable> itemsEnviables = new ArrayList<ItemEnviable>(); 
						lstOptRecibos.get().stream().forEach(reb ->{
							ItemEnviable item = (ItemEnviable) reb;
							itemsEnviables.add(item);
						});
						
						mailService.enviarRecibos(new DestinatarioEntity(user, destinatario.getEmail(), "recibos de sueldo", destinatario.isMultipleFile(),itemsEnviables));
						this.reciboService.registrarEnviado(lstOptRecibos.get());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		});
	}




	private User obtenerUsuario(String userName) {
		try {
			return userService.findByUserName(userName);
		} catch (Exception e) {

		}
		return null;
	}




	@Override
	public CompletableFuture<?> enviarEmailV2(Enviables enviables, String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}

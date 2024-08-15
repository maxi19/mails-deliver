package com.turnero.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.component.SenderService;
import com.turnero.dto.DestinatarioEntity;
import com.turnero.dto.Enviables;
import com.turnero.dto.ItemEnviable;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.service.ReciboService;
import com.turnero.service.UserService;

@Service
public class EmailManagerImp implements EmailManager{

	@Autowired
	private UserService userService;

    @Autowired
    private SenderService senderService;

    @Autowired
    private ReciboService reciboService;
    
    private static final String SUBJECT ="RECIBOS DE SUELDO";

	private static final Logger log =  LoggerFactory.getLogger(EmailManagerImp.class);

    
	@Override
	public void enviarEmail(Enviables enviables, String userName) throws Exception{

		User user = userService.findByUserName(userName);
		
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
						
							senderService.enviarRecibos(new DestinatarioEntity(user, destinatario.getEmail(), SUBJECT, destinatario.isMultipleFile(),itemsEnviables));
						
							this.reciboService.registrarEnviado(lstOptRecibos.get());							
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
		});
	}

}

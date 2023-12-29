MailDeliver
Esta aplicacion fue desarrollada para la secretaria del colegio Fatima su objetivo que agilizar el envio de los reciboc de sueldo.
=================

angular y springboot 

#arma el paquete para prod y dev
-mvn clean package -Pprod
-mvn clean package -Pdev
 
#compìla los archivos ts. 
ng build --base-href=/angular/
 
#compìla los archivos ts. para ambiente prod
-npm run build:dev
-npm run build:prod

npm build:prod --base-href=/angular/
build production
ng build --c=production --base-href=/angular/
 
#compìla para ejecucion local y prod
-npm run start:dev
-npm run start:prod
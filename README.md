# Camper_App

#Clone repository
git clone https://github.com/ale89-pit/Camper_App.git

#Una volta clonata la repo creare un database chiamandolo camper_app e fare il Restore tramite il file camper_app_backup nella cartella Camper_App_Server

#Aprire la cartella Camper_App_Server tramite l'ide e runnare come Spring boot App.

#Adesso siamo pronti per usera l'API.

# Endpoint

#User Register

`http://localhost:8080/api/auth/register`

{
"nome":"example",
"cognome":"example",
"userName":"example",
"email":"example@example.it",
"password":"example"  
}

#User Login
`http://localhost:8080/api/auth/login`
{
"userName":"example",
"password":"example"
}

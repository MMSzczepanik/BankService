package SDA;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    protected static final Logger logger = Logger.getLogger("Sessi onManager");
    private static SessionManager ourInstance = new SessionManager();
    private List<Client> loggerList = new ArrayList<>();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public boolean logIn(String email, String password) throws SQLException {
        Client client  = DataBaseService.getClientByEmail(email);
        if(client.getPassword().equals(password)){
            loggerList.add(client);
            logger.info("Zalogowany " + client);
            return true;
        }else{
            return false;
        }

    }

    public void unLog (Client client){
        loggerList.remove(client);
    }

    public boolean isLogIn(Client client){
        return loggerList.contains(client);
    }
}

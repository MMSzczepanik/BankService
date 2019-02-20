package SDA;

import SDA.Exceptions.BadAccountNumberException;
import SDA.Exceptions.DebetException;

import java.sql.SQLException;

public class TransferSystem {

    public boolean transfer(Client client, String name, String lastName, String adress, String accountNumber, float prize) throws BadAccountNumberException, SQLException, DebetException {


        if(client.getCard().getPrize()>=prize){
            if(accountIsOk(name, lastName, adress, accountNumber)){
                Client forClient = DataBaseService.getClientByAccountNumber(accountNumber);
                client.getCard().setPrize(client.getCard().getPrize()-prize);
                forClient.getCard().setPrize(forClient.getCard().getPrize()+prize);
                DataBaseService.updateAccount(client);
                DataBaseService.updateAccount(forClient);
                return true;
            }
        }

        throw new DebetException();

    }

    private boolean accountIsOk(String name, String lastName, String adress, String accountNumber) throws BadAccountNumberException, SQLException {
        Client client = DataBaseService.getClientByAccountNumber(accountNumber);
        if(name.equals(client.getName()) && lastName.equals(client.getLastName()) && adress.equals(client.getAdress())){
            return true;
        }
        return false;
    }


}

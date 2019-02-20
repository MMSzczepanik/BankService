package SDA;


import SDA.Exceptions.BadAccountNumberException;
import SDA.Exceptions.DebetException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{
    public static final String DBSURL = "jdbc:sqlite:Baza";



    public static void main( String[] args ) {

        try {
            Client client = new Client("Marekkk", "Konrad", "Warszawa", "1993-10-20", "92102010245", "mkonrad", "yyy", "mko@o2.pl");
            Client client1 = new Client("Ewa", "Jak", "Warszawa", "1993-10-20", "92102010911", "eaajak", "sss", "ejffak@gmail.com");
            client.getCard().setPrize(1000f);


            System.out.println(SessionManager.getInstance().logIn("mko@o2.pl", "yyy"));
            System.out.println(SessionManager.getInstance().isLogIn(client));

            if(SessionManager.getInstance().isLogIn(client)) {
            TransferSystem transferSystem = new TransferSystem();
            transferSystem.transfer(client, "Ewa", "Jak", "Warszawa", "43650786353773246767825265",
                    200f);
               }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (BadAccountNumberException e) {
            e.printStackTrace();
        } catch (DebetException e) {
            e.printStackTrace();

        }
    }
}

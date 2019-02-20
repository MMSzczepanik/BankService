package SDA;

import SDA.Exceptions.BadAccountNumberException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    public static void add(Client client) throws SQLException, IOException {
        String databaseUrl = App.DBSURL;
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
            TableUtils.createTableIfNotExists(connectionSource, Client.class);
            Dao<Client, String> accountDao = DaoManager.createDao(connectionSource, Client.class);
            accountDao.create(client);
            connectionSource.close();
            add(client.getCard());

    }

    public static void add(Card card) throws SQLException {
        String databaseUrl = App.DBSURL;
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Card.class);
        Dao<Card, String> accountDao = DaoManager.createDao(connectionSource, Card.class);
        accountDao.create(card);

    }

    public static List<Client> getAllClient() throws SQLException {
        String databaseUrl = App.DBSURL;
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Card.class);
        Dao<Client, String> accountDao = DaoManager.createDao(connectionSource, Client.class);
        List<Client> clientList = new ArrayList<Client>();
        clientList = accountDao.queryForAll();


        return clientList;
    }

    public static Client getClientByEmail(String email) throws SQLException {
        String databaseUrl = App.DBSURL;
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Client.class);
        Dao<Client, String> accountDao = DaoManager.createDao(connectionSource, Client.class);
        List<Client> clientList = new ArrayList<Client>();
        clientList = accountDao.queryBuilder().where().eq("email", email).query();
        TableUtils.createTableIfNotExists(connectionSource, Card.class);
        Dao<Card, String> cardDao = DaoManager.createDao(connectionSource, Card.class);

        for(Client client : clientList){
            client.setCard(cardDao.queryBuilder().where().eq("cardNumber", client.getCard().getCardNumber()).query().get(0));
        }

        return clientList.get(0);
    }



    public static Client getClientByAccountNumber(String accountNumber) throws SQLException, BadAccountNumberException {
        String databaseUrl = App.DBSURL;
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Card.class);
        Dao<Card, String> cardDao = DaoManager.createDao(connectionSource, Card.class);
        List<Card> cardList = new ArrayList<Card>();
        cardList = cardDao.queryBuilder().where().eq("accountNumber", accountNumber).query();
        System.out.println(cardList.size());

        if(cardList.size()==0){
            throw new BadAccountNumberException();
        }


        Dao<Client, String> accountDao = DaoManager.createDao(connectionSource, Client.class);
        List<Client> clientList = new ArrayList<Client>();
        clientList = accountDao.queryBuilder().where().eq("card_id", cardList.get(0).getCardNumber()).query();

        return clientList.get(0);
    }

    public static void updateAccount(Client client) throws SQLException {
        String databaseUrl = App.DBSURL;
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Client.class);
        Dao<Client, String> accountDao = DaoManager.createDao(connectionSource, Client.class);
        TableUtils.createTableIfNotExists(connectionSource, Card.class);
        Dao<Card, String> cardDao = DaoManager.createDao(connectionSource, Card.class);
        accountDao.update(client);
        cardDao.update(client.getCard());
    }

}

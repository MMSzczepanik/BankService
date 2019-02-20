package SDA;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable(tableName = "Client")
public class Client {

    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String lastName;
    @DatabaseField(canBeNull = false)
    private String adress;
    @DatabaseField(canBeNull = false)
    private String date;
    @DatabaseField(canBeNull = false)
    private String pesel;
    @DatabaseField(id = true)
    private String login;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField(canBeNull = false, unique = true)
    private String email;
    @DatabaseField(canBeNull = false,  foreign = true)
    private Card card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


    public Client() {
    }

    public Client(String name, String lastName, String adress, String date, String pesel, String login, String password, String email) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.date = date;
        this.pesel = pesel;
        this.login = login;
        this.password = password;
        this.email = email;
        this.card = new Card();
        this.card.generateValue();
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adress='" + adress + '\'' +
                ", date='" + date + '\'' +
                ", pesel='" + pesel + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", card=" + card +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(adress, client.adress) &&
                Objects.equals(date, client.date) &&
                Objects.equals(pesel, client.pesel) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(email, client.email) &&
                Objects.equals(card, client.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, adress, date, pesel, login, password, email, card);
    }
}

package SDA;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;
import java.util.Random;

@DatabaseTable(tableName = "Card")
public class Card {
    @DatabaseField(canBeNull = false)
    private String accountNumber = "";
    @DatabaseField(canBeNull = false)
    private float prize = 0.0f;
    @DatabaseField(id = true)
    private String cardNumber = "";
    @DatabaseField(canBeNull = false)
    private int pin = 0000;

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean setAccountNumber(String accountNumber) {
        String newAccountNumber = accountNumber.replace(" ", "");
        if(newAccountNumber.length()<26){
            return false;
        }
        this.accountNumber = accountNumber;
        return true;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {

        this.prize = prize;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public boolean setPin(int pin) {
        if(pin >999 && pin < 10000) {
            this.pin = pin;
            return true;
        }
        return false;
    }


    public Card() {
    }

    public void generateValue(){
        Random rand = new Random();
        for(int i=0; i<26; i++){
            this.accountNumber+=rand.nextInt(9);
        }

        for(int i=0; i<6; i++){
            this.cardNumber+=rand.nextInt(9);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Float.compare(card.prize, prize) == 0 &&
                pin == card.pin &&
                Objects.equals(accountNumber, card.accountNumber) &&
                Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, prize, cardNumber, pin);
    }
}

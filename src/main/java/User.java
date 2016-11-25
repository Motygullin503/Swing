/**
 * Created by UseR7 on 23.11.2016.
 */
public class User {
    int id;
    String name;
    String surname;
    int number;
    String choice;
    boolean gender;

    public User(String name, String surname, int number, String choice, boolean gender) {

        this.name = name;
        this.surname = surname;
        this.number = number;
        this.choice = choice;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}

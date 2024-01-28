package DavidRios.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    @Column(nullable = false, unique = true)
    private long loyaltyCardNumber;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    // CONSTRUCTORS
public User() {}

    public User(String name, String surname, LocalDate birthday, long loyaltyCardNumber) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.loyaltyCardNumber = loyaltyCardNumber;
    }

    //OVERRIDES

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", loyaltyCardNumber=" + loyaltyCardNumber +
                ", loans=" + loans +
                '}';
    }


    // GETTERS

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public long getLoyaltyCardNumber() {
    return loyaltyCardNumber;
    }

    public List<Loan> getLoans () { return loans;}

    // SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setLoyaltyCardNumber(long loyaltyCardNumber) {this.loyaltyCardNumber = loyaltyCardNumber;}
}

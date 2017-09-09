package dva.domain;

import javax.persistence.*;

/**
 * Created by kar on 7/9/17.
 */
@Entity
public class Claim {
    @GeneratedValue
    @Id
    private Long id;
    private String number;
    private String description;
    private Double amount;

    Claim() {

    }

    Claim(Person person, String number, String description, Double amount) {
        this.number = number;
        this.description = description;
        this.amount = amount;
        this.person = person;
    }

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() { return id; }

    public String getNumber() {
        return number;
    }

    public Person getPerson() { return person; }

    public String getDescription() { return description; }
    public Double getAmount() { return amount; }

    public String toString() {
        return number + "," + description + "," + amount;
    }
}

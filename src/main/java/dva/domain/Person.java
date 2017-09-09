package dva.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by kar on 7/9/17.
 */
@Entity
public class Person {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String phone;
    private String email;

    Person() {
        this("test person", "0000 0000", "test.person@test.com");
    }

    Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() {
        return email;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy="person",cascade = CascadeType.MERGE)
    private Set<Claim> claims;

    public Set<Claim> getClaims() {
        return claims;
    }

    public Double getTotalClaimed() {
        Double total = 0.0;
        for (Claim c : claims) {
            total += c.getAmount();
        }
        return total;
    }

    public String toString() {
        return id + "," + name + "," + phone + "," + email;
    }}

package code.gym.webmediaplayer.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username; //Cần validate unique
    @Size(min = 2, max = 30)
    private String password; //Cần validate 6-8 ký tự
    @Size(min = 10, max = 12)
    private String phone; //Cần validate 12 ky tu
    @Email
    private String email; //Cần validate @
    private String address;
    private Boolean block;
    @ManyToOne
    private Role role;

    public Account() {
    }

    public Account(Long id, String username, String password, String phone, String email, String address, Boolean block, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.block = block;
        this.role = role;
    }
}

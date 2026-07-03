package com.example.entity;

import com.example.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Column(unique = true)
    private String email;
    private String phone;


    private String password;

//    //ACTIVE,INACTIVE,BLOCKED
//    @Column(nullable = false)
//    private String status = " ACTIVE";

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role = new HashSet<>();

    private Instant createdAt = Instant.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DrivingLicense drivingLicense;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Booking> booking;

    @PrePersist
    protected void onCreate() {

        if (createdAt == null) {
            createdAt = Instant.now();
        }

        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }
}

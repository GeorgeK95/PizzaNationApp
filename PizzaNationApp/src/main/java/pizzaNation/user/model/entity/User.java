package pizzaNation.user.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pizzaNation.app.model.entity.Log;
import pizzaNation.app.model.entity.Order;
import pizzaNation.user.enumeration.Gender;

import javax.persistence.*;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false)
    private String address;

    private Gender gender;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String city;

    private Boolean emailNewsletters;

    @Transient
    private Boolean isAccountNonExpired;

    @Transient
    private Boolean isAccountNonLocked;

    @Transient
    private Boolean isCredentialsNonExpired;

    private Boolean isEnabled;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities;

    @Column(nullable = false)
    private Date date;

    private String emailVerificationCode;

    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    public User() {
        this.authorities = new HashSet<>();
        this.emailVerificationCode = UUID.randomUUID().toString();
        this.date = new Date();
        this.isEnabled = false;
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String firstName, String lastName, String address, Gender gender,
                String phone, String city, Boolean emailNewsletters) {
        this(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.city = city;
        this.emailNewsletters = emailNewsletters;
        this.date = new Date();
    }

    @PrePersist
    public void onPrePersist() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    public void addRole(Role role) {
        this.authorities.add(role);
    }

    //GETTERS

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public Boolean getEmailNewsletters() {
        return emailNewsletters;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public String getId() {
        return id;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    //SETTERS

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailNewsletters(Boolean emailNewsletters) {
        this.emailNewsletters = emailNewsletters;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(Date localDate) {
        this.date = localDate;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

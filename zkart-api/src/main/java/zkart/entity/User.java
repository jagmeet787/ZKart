package zkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String firstName;
  private String lastName;
  private String userType;
  @Column(unique=true)
  private String email;
  @Column(unique=true)
  private String phone;
  private String imageUrl;
  private String password;
  private String address;
  private Integer wallet;
  private String secret;
  private String dob;////
  public User() {
  }
  public User(Integer userId) {
    this.id = userId;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getUserType() {
    return userType;
  }
  public void setUserType(String userType) {
    this.userType = userType;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public Integer getWallet() {
    return wallet;
  }
  public void setWallet(Integer wallet) {
    this.wallet = wallet;
  }
  public String getSecret() {
    return secret;
  }
  public void setSecret(String secret) {
    this.secret = secret;
  }
  public String getDob() {
    return dob;
  }
  public void setDob(String dob) {
    this.dob = dob;
  }
}
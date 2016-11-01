package club.DAO.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import club.password.PasswordHandler;

@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name="User.findAll", query="SELECT u FROM User u WHERE u.deleted=false"),
    @NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE  u.email = :email AND u.deleted=false"),
    @NamedQuery(name="User.findByCpcid", query="SELECT u FROM User u WHERE  u.cpcid = :cpcid AND u.deleted=false")
}) 
public class User implements Serializable {

	public enum ApprovedState {
		GRANTED, DENIED, PENDING
	};

	private static final long serialVersionUID = 7589032287673546267L;
	
	@Transient
	private String password;
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private boolean admin;

	@Column(name="deleted", nullable=false)
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Column(name="approved_state", nullable=false)
	private int approvedState;

	@Column(nullable=false, length=60)
	private String email;

	@Column(nullable=true, length=32)
	private String cpcid;
	
	public String getCpcid() {
		return cpcid;
	}

	public void setCpcid(String cpcid) {
		this.cpcid = cpcid;
	}

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="birthday", nullable=false, length=8)
	private Date birthday;

	@Column(name="hmac_password", nullable=false, length=161)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String HMACPassword;

	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public ApprovedState getApprovedState() {
		return ApprovedState.values()[this.approvedState];
	}

	public void setApprovedState(ApprovedState approvedState) {
		this.approvedState = approvedState.ordinal();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		System.out.println("LASTNAME: " + lastName);
		this.lastName = lastName;
	}

	@JsonIgnore
	public String getHashedPasswordSaltpair() {
		return HMACPassword;
	}
	
	public void generateNewHMACpassword(String password) {		
		HMACPassword = PasswordHandler.hash(password).toString();	
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getHMACPassword() {
		return HMACPassword;
	}
	
	public void setHMACPassword(String HMACPassword) {
		System.out.println("setter kors");
		generateNewHMACpassword(HMACPassword);
	}

	public String getFullName(){
		return this.firstName + " " + this.lastName;
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return user.getId() == this.getId();
	}
	
	

}
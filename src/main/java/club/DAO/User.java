package club.DAO;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import club.password.CouldNotEncryptPasswordException;
import club.password.PasswordHandler;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
    @NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE  u.email = :email")
}) 
public class User implements Serializable {

	public enum ApprovedState {
		GRANTED, DENIED, PENDING
	};

	private static final long serialVersionUID = 7589032287673546267L;
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private boolean admin;

	@Column(name="approved_state", nullable=false)
	private int approvedState;

	@Column(nullable=false, length=60)
	private String email;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="birthday", nullable=false, length=8)
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name="hmac_password", nullable=false, length=161)
	private String hMACPassword;


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
		this.lastName = lastName;
	}

	@JsonIgnore
	public String getHashedPasswordSaltpair() {
		return hMACPassword;
	}
	
	public void generateNewHMACpassword(String password) {		
		hMACPassword = PasswordHandler.hash(password).toString();	
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
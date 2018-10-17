package com.png.data.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;


/**
 * Created by Arindam on 5/28/2017.
 */

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long idUser;

    @Column (name="email")
    @Email
    @NotEmpty
    private String email;

    @Column (name = "email_validated")
    private Boolean emailValidated = false;

    @Column (name = "email_validation_code")
    private String emailValidationCode;

    @Column (name = "email_sent_timestamp")
    private Timestamp emailSentTimestamp;

    @Column (name = "email_valid_upto_timestamp")
    private Timestamp emailValidUptoTimestamp;

    @Column (name="password")
    @Length (min = 5)
    @NotEmpty
    private String password;

    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;

	@Column (name= "last_login_timestamp")
    private Timestamp lastLoginTimestamp;

    @Column (name= "last_logoff_timestamp")
    private Timestamp lastLogOffTimestamp;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;

	@Column(name = "deleted_flag")
    private Boolean deleteFlag =false;

    @Transient
    private String confirmPassword;


	@ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Boolean getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Boolean emailValidated) {
        this.emailValidated = emailValidated;
    }

    public String getEmailValidationCode() {
        return emailValidationCode;
    }

    public void setEmailValidationCode(String emailValidationCode) {
        this.emailValidationCode = emailValidationCode;
    }

    public Timestamp getEmailSentTimestamp() {
        return emailSentTimestamp;
    }

    public void setEmailSentTimestamp(Timestamp emailSentTimestamp) {
        this.emailSentTimestamp = emailSentTimestamp;
    }

    public Timestamp getEmailValidUptoTimestamp() {
        return emailValidUptoTimestamp;
    }

    public void setEmailValidUptoTimestamp(Timestamp emailValidUptoTimestamp) {
        this.emailValidUptoTimestamp = emailValidUptoTimestamp;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Timestamp getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public Timestamp getLastLogOffTimestamp() { return lastLogOffTimestamp; }

    public void setLastLogOffTimestamp(Timestamp lastLogOffTimestamp) {
        this.lastLogOffTimestamp = lastLogOffTimestamp;
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
	
	public void trimUserData(){
		this.firstName = this.firstName.trim();
		this.lastName = this.lastName.trim();
		this.email = this.email.trim();
	}
	public String generateEmailValidationCode(){
        String validationCode = UUID.randomUUID().toString();
        return validationCode;
    }
}
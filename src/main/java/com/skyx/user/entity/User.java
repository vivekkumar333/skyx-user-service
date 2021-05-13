package com.skyx.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USER_NAME")
    private String name;
	
	@Column(name = "PHONE_NO")
    private String phone;
	
	@Column(name = "EMAIL_ID")
    private String email;
	
	@Column(name = "PASSWORD")
    private String password;
    
    //LoginStatus : 1:Active , 2:Blocked
    @ColumnDefault("1")
    @Column(name = "LOGIN_STATUS")
    private int loginStatus;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Card> cards;
}

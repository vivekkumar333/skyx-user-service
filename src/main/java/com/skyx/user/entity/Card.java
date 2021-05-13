package com.skyx.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "tbl_card")
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private Long id;
	
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	
	@Column(name = "CARD_NUMBER")
	private Long cardNumber;
	
	@Column(name = "CARD_EXPIRY_MONTH")
	private String expiryMonth;
	
	@Column(name = "CARD_EXPIRY_YEAR")
	private String expiryYear;
	
	/**
	 *  1 : Active
	 *  2 : Blocked
	 * */
	@Column(name = "CARD_STATUS")
	private int status;
	
	@JsonIgnore
	@JoinColumn(name = "fk_USER_ID")
	@ManyToOne
	private User user;
}

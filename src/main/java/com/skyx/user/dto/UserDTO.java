package com.skyx.user.dto;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.skyx.user.entity.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class UserDTO {
	private Long id;
	
    private String name;
    
    private String phone;
    
    private String email;
    
    private Integer loginStatus;
    
    private List<Card> cards;
}

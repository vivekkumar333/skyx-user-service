package com.skyx.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyx.user.dto.UserDTO;
import com.skyx.user.entity.Card;
import com.skyx.user.entity.User;
import com.skyx.user.repo.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final int USER_LOGIN_STATUS_ACTIVE = 1;
	//private static final int USER_LOGIN_STATUS_BLOCKED = 2;
	
	private static final int CARD_STATUS_ACTIVE = 1;
	//private static final int CARD_STATUS_BLOCKED = 2;

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/email/{email}/password/{password}")
	public UserDTO userLogin(@PathVariable String email, @PathVariable String password){
		UserDTO userDto = new UserDTO();
		try {
			Optional<User> user = userRepo.findByEmail(email);
			if(user.isPresent()) {
				if(user.get().getPassword().equals(password)) {
					if(user.get().getLoginStatus() == USER_LOGIN_STATUS_ACTIVE) {
						List<Card> cards = user.get().getCards();
						List<Card> tempCard = new ArrayList<Card>();
						for(Card card : cards) {
							if(card.getStatus() == CARD_STATUS_ACTIVE) {
								tempCard.add(card);
							}
						}
						userDto.setId(user.get().getId());
						userDto.setName(user.get().getName());
						userDto.setEmail(user.get().getEmail());
						userDto.setPhone(user.get().getPhone());
						userDto.setLoginStatus(user.get().getLoginStatus());
						userDto.setCards(tempCard);
					}else {
						throw new RuntimeException("Blocked User! Please Contact to Admin");
					}
				}else {
					throw new RuntimeException("Invalid Password! Please enter correct Password" );
				}
			}
		}catch(NoSuchElementException ex){
			throw new NoSuchElementException("Invalid User! User is not registred");
		}
		return userDto;
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody User user){
		user.setLoginStatus(1);
		User savedUser = userRepo.save(user);
		UserDTO userDto = new UserDTO();
		userDto.setId(savedUser.getId());
		userDto.setName(savedUser.getName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPhone(savedUser.getPhone());
		userDto.setLoginStatus(savedUser.getLoginStatus());
		
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);	
	}
	
	@GetMapping("/{userId}")
	public UserDTO userLogin(@PathVariable Long userId){
		UserDTO userDto = new UserDTO();
		try {
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent()) {
					if(user.get().getLoginStatus() == USER_LOGIN_STATUS_ACTIVE) {
						List<Card> cards = user.get().getCards();
						List<Card> tempCard = new ArrayList<Card>();
						for(Card card : cards) {
							if(card.getStatus() == CARD_STATUS_ACTIVE) {
								tempCard.add(card);
							}
						}
						
						userDto.setId(user.get().getId());
						userDto.setName(user.get().getName());
						userDto.setEmail(user.get().getEmail());
						userDto.setPhone(user.get().getPhone());
						userDto.setLoginStatus(user.get().getLoginStatus());
						userDto.setCards(tempCard);
						
					}else {
						throw new RuntimeException("Blocked User! Please Contact to Admin");
					}
				}
		}catch(NoSuchElementException ex){
			throw new NoSuchElementException("Invalid User! User is not registred");
		}
		return userDto;
	}
	
	
}

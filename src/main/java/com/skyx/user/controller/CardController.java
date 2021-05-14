package com.skyx.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyx.user.dto.AddCardDTO;
import com.skyx.user.entity.Card;
import com.skyx.user.entity.User;
import com.skyx.user.repo.CardRepo;

@RestController
@RequestMapping("/card")
public class CardController {
	
	private static final int CARD_STATUS_ACTIVE = 1;
	//private static final int CARD_STATUS_BLOCKED = 2;

	@Autowired
	CardRepo cardRepo;
	
	@PostMapping("/addcard/")
	public ResponseEntity<String> createCard(@RequestBody AddCardDTO addCardDTO){
		
		User user = new User();
		user.setId(addCardDTO.getUserId());
		
		Card card = new Card();
		card.setUser(user);
		card.setCardHolderName(addCardDTO.getCardHolderName());
		card.setCardNumber(addCardDTO.getCardNumber());
		card.setExpiryMonth(addCardDTO.getExpiryMonth());
		card.setExpiryYear(addCardDTO.getExpiryYear());
		card.setStatus(CARD_STATUS_ACTIVE);
		Card savedCard = cardRepo.save(card);
		
		String cardNo = savedCard.getCardNumber().toString().substring(6, 10);
		String msg= "Card Ending with "+cardNo+" Saved Successfully";
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}	
	
}

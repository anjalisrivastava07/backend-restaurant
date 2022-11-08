package org.bct.springboot.controller;

import org.bct.springboot.model.Payment;
import org.bct.springboot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRespository;
	
	@PostMapping("/payment")
	public Payment createPayment(@Validated @RequestBody Payment payment) {
		return paymentRespository.save(payment);
	}
	

}

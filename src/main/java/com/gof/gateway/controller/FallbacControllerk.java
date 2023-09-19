package com.gof.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbacControllerk {
	@RequestMapping("/orderFallBack")
	public Mono<String> orderServiceFallBack(){
		return Mono.just("order service is taking  too much time to response is down. please try again later ");
		
	}

	@RequestMapping("/paymentFallBack")
	public Mono<String> paymentServiceFallBack(){
		return Mono.just("Payment service is taking  too much time to response or is down. please try again later ");
		
	}

}

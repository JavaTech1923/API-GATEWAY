package com.gof.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import com.gof.gateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RouteValidator routeValidator;
	@Autowired
	private JwtUtil jwtUtil;
	
//	@Autowired
//	private RestTemplate template;
//	
	public AuthenticationFilter() {
		super(Config.class);
		// TODO Auto-generated constructor stub
	}

//	public AuthenticationFilter(Class<Config> configClass) {
//		super(configClass);
//		// TODO Auto-generated constructor stub
//	}

	public static class Config{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		
		return ((exchange, chain)->{
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization");
				}
				String authHeaders=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeaders!=null && authHeaders.startsWith("Bearer ")) {
					authHeaders=authHeaders.substring(7);
					
				}
				try {
					//rest call to auth service
//					template.getForObject("http://AUTH-SERVICE/auth/validate?token"+authHeaders, String.class);
					jwtUtil.validateToken(authHeaders);
					
				}catch(Exception e) {
					System.out.println("Invalid Access......!");
					throw new RuntimeException("un AUthorized Access to Application");
				}
			}
			return chain.filter(exchange);
		});
		
	}

}

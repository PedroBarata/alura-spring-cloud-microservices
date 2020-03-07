package br.com.barata.microservice.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
public class LojaApplication {
	
	//Interceptor do Feign, usado para repassar o token
	@Bean
	public RequestInterceptor getAuthToken() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				/* Só funciona após usar a configuração de 
				 * compartilhamento de informações do security com o Hystrix!*/
				//Pegamos o token com essa instância do Spring Security
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if(auth == null) {
					return;
				}
				OAuth2AuthenticationDetails oAuthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
				//E adicionamos ao Header do Feign
				template.header("Authorization", "Bearer " + oAuthDetails.getTokenValue());
			}
		};
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}

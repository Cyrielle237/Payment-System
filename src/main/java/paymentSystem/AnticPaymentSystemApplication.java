package paymentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan( basePackages = {"paymentSystem.entity.rh", "paymentSystem.entity.ref", "paymentSystem.entity.paie", "paymentSystem.security"})
@SpringBootApplication
public class AnticPaymentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnticPaymentSystemApplication.class, args);
	}

}

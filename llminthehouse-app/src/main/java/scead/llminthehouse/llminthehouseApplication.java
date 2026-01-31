package scead.llminthehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class llminthehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(llminthehouseApplication.class, args);
	}

}

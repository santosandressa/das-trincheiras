package academy.devdojo.spring_boot_das_trincheiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"outside.devdojo, academy.devdojo.spring_boot_das_trincheiras"})
public class SpringBootDasTrincheirasApplication {

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(SpringBootDasTrincheirasApplication.class, args);
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.forEach(System.out::println);
	}

}

package kai.example.timeTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kai.example.timeTable"})
public class TimeTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTableApplication.class, args);
	}

}

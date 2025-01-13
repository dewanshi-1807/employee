package com.employeeCurd.demo;//package com.employeeCurd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

///import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//@EntityScan("com.employeeCurd.demo")
//public class DemoApplication {

	//public static void main(String[] args) {



//}



//package com.employeeCurd.demo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//public class DemoApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

//	}
//}


@SpringBootApplication(scanBasePackages = {"com.employeeCurd.demo.learn"})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

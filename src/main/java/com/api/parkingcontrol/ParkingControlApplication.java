package com.api.parkingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ParkingControlApplication {

	public static void main(String[] args) {SpringApplication.run(ParkingControlApplication.class,args);}

		@GetMapping("/")
		public String index(){
			return "Acesse meu link do GitHub para ver as funcionalidades desta API, ela necessita de um banco de dados PostgreSql para persistir os dados." +
					" https://github.com/matheuslongo/parking-control-spring";
		}

	}



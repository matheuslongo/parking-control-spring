package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.controllers.dto.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
/*
 * As classes contidas no package "com.api.parkingcontrol.controllers", são responsáveis
 * por receber as requisições HTTP e devem ter a anotoção @RestController, esta classe
 * recebe as requisições que disponibilizam as operações necessárias para controlar o
 * estacionamento em um condomínio.
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    /**
     * Injeção de dependencia de parkingSportService
     */
    @Autowired
    ParkingSportService parkingSportService;

    /**
     * O método abaixo é reponsável por receber requisições do tipo POST, em verbo HTTP
     * indica que o serviço disponibilizado cria um novo recurso e devolve uma resposta
     * com o HTTP status igual a 201.
     * @param parkingSpotDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (parkingSportService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Lincense PLate Car is already in use!");
        }
        if (parkingSportService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot Number is already in use!");
        }
        if (parkingSportService.existsByApartamentAndBlock(parkingSpotDto.getApartament(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot Already registered for this apartament/block!");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSportService.save(parkingSpotModel));

    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(parkingSportService.findAll(pageable))  ;

    }

    /**
     * O método abaixo responde requisiçoes do tipo GET, com o parâmetro de busca "id",
     * ou seja ele retorna uma busca feita através do id, devolvento os valores que estão no "id"
     * setado no parâmetro.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSportService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    /**
     * O método abaixo responde requisições do tipo DELETE, este método é responsável por
     * DELETAR recuros através do parâmetro "id", sendo assim excluindo as informações contidas
     * no "id" setado.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSportService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found!");
        }
        parkingSportService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot Delete Succesfully!");
    }

    /**
     * O método abaixo responde requisições do tipo PUT, tem como responsabilidade atualizar informações
     * de um recurso já criado, usando como referencia o "id" setado na URL .
     * @param id
     * @param parkingSpotDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        try {
            Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSportService.findById(id);
            if (!parkingSpotModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found!");
            }
            var parkingSpotModel = parkingSpotModelOptional.get();
            parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
            parkingSpotModel.setApartament(parkingSpotDto.getApartament());
            parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
            parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
            parkingSpotModel.setBlock(parkingSpotDto.getBlock());
            parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
            parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
            parkingSpotModel.setId(id);
            parkingSpotModel.setRegistrationDate(parkingSpotDto.getRegistrationDate());
            // parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
            return ResponseEntity.status(HttpStatus.OK).body(parkingSportService.save(parkingSpotModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

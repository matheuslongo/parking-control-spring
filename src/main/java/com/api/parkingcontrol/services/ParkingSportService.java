package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * As classes contidas no package "com.api.parkingcontrol.services" são responsáveis por
 * conter as regras de negócio da API, essas classes devem estar anotadas com @Service,
 * os métodos contidos na camada de serviço são resposáveis
 * por fazer chamadas ao Repositório e também a outros serviços.
 */
@Service
public class ParkingSportService {
    /**
     * Injeção de dependecia do parkingSpotRepository
     */
    @Autowired
    ParkingSpotRepository parkingSpotRepository;
    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }
    /**
     * O método abaixo, verifica se uma placa já foi cadastrada.
     * @param licensePlateCar
     * @return
     */
    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }
    /**
     * O método abaixo, verifica se uma vaga já está em uso .
     * @param parkingSpotNumber
     * @return
     */
    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }
    /**
     * O método abaixo, verifica se um Bloco e Apartamento já estão cadastrados.
     * @param apartament
     * @param block
     * @return
     */
    public boolean existsByApartamentAndBlock(String apartament, String block) {
      return   parkingSpotRepository.existsByApartamentAndBlock(apartament, block);
    }
    /**
     * O método abaixo, é responsável por fazer a paginação nas buscas.
     * @param pageable
     * @return
     */
    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return  parkingSpotRepository.findAll(pageable);
    }
    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }
    /**
     * O método abaixo, é responsável por deletar um recurso.
     * @param parkingSpotModel
     */
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }
}

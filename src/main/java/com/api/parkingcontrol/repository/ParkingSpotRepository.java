package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
/**
 *  Atuará como um repositório de banco de dados.
 */
@Repository
public interface ParkingSpotRepository  extends JpaRepository<ParkingSpotModel, UUID> {
    /**
     *  Atuará como um repositório de :
     * @param licensePlateCar
     * @return
     */
    boolean existsByLicensePlateCar(String licensePlateCar);
    /**
     *  Atuará como um repositório de :
     * @param parkingSpotNumber
     * @return
     */
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    /**
     *  Atuará como um repositório de :
     * @param apartament
     * @param Block
     * @return
     */
    boolean existsByApartamentAndBlock(String apartament, String Block);



}

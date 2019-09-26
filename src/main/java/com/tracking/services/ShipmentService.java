package com.tracking.services;

import com.tracking.entities.Shipment;
import com.tracking.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    /**
     * Verifies the user input.
     * Thymeleaf deliberately enters the shipping method in order to process the user input as a string.
     */
    public Shipment verifyingShipment(Shipment shipment){

        Shipment foundShipment = new Shipment();
        foundShipment.setShipmentNumber(null);

        if (shipment.getShippingMethod().matches("[0-9]+")) {

            shipment.setShipmentNumber(Integer.parseInt(shipment.getShippingMethod()));

            // Verifies whether the entered shipment number is contained in the database.
            if (shipmentRepository.existsById(shipment.getShipmentNumber())) {

                Optional<Shipment> optionalShipment = shipmentRepository.findById(shipment.getShipmentNumber());
                foundShipment = optionalShipment.get();
                formatDate(foundShipment);
            }
        }

    return foundShipment;
    }
    /**
     * Formatted submission date and delivery date for display on the html
     */
    public void formatDate(Shipment foundShipment) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd. MMMM yyyy");

        foundShipment.setFormattedSubmissionDate(foundShipment.getSubmissionDate().format(formatter));
        foundShipment.setFormattedDeliveryDate(foundShipment.getDeliveryDate().format(formatter));
    }
}

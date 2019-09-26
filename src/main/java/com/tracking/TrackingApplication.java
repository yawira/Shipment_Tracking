package com.tracking;

import com.tracking.entities.Customer;
import com.tracking.entities.Shipment;
import com.tracking.repositories.CustomerRepository;
import com.tracking.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class TrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    /**
     * Automatic preparation and filling of the database with Spring.
     */
    @PostConstruct
    public void fillDatabase() {

        Customer leeKoer = new Customer("Lee", "Kör", "Liefergasse", "1", "40213",
                "Düsseldorf", "Germany");

        Customer bobFahrer = new Customer("Bob", "Fahrer", "Große Elbstraße", "135", "22767",
                "Hamburg", "Germany");

        Customer heinBloed = new Customer("Hein", "Blöd", "Emilienstraße", "20", "01139",
                "Dresden", "Germany");

        Customer annaBude = new Customer("Anna", "Bude", "Friesenstraße", "29", "19059",
                "Schwerin", "Germany");

        Customer reinerZufall = new Customer("Reiner", "Zufall", "Fraunhoferstraße", "42", "80469",
                "München", "Germany");

        Customer andiMauer = new Customer("Andi", "Mauer", "Zülpicher Str.", "9", "50674",
                "Köln", "Germany");

        Shipment sendung1 = new Shipment("Standard", "parcel deposited", LocalDate.now(),
                LocalDate.now().plusYears(15).plusDays(56), leeKoer, bobFahrer);

        Shipment sendung2 = new Shipment("Express", "in delivery", LocalDate.now().minusDays(1),
                LocalDate.now(), heinBloed, annaBude);

        Shipment sendung3 = new Shipment("Standard", "delivered", LocalDate.now().minusYears(77),
                LocalDate.now().minusYears(5), reinerZufall, andiMauer);

        customerRepository.save(leeKoer);
        customerRepository.save(bobFahrer);
        customerRepository.save(heinBloed);
        customerRepository.save(annaBude);
        customerRepository.save(reinerZufall);
        customerRepository.save(andiMauer);

        shipmentRepository.save(sendung1);
        shipmentRepository.save(sendung2);
        shipmentRepository.save(sendung3);
    }
}

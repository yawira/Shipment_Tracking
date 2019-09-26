package com.tracking;


import com.tracking.entities.Shipment;
import com.tracking.repositories.ShipmentRepository;
import com.tracking.services.ShipmentService;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;


/**
 * Logik in der SendungsService Klasse wird getestet.
 */
public class ShipmentServiceTest {

    // Mock für simulierte Datenbankanbindung
    @Mock
    private ShipmentRepository sendungsRepository;

    @InjectMocks
    private ShipmentService sendungsService;

    @InjectMocks
    private Shipment sendung = new Shipment();

    /**
     * Dieser Test prüft das korrekte Verhalten bei Eingabe einer korrekten Sendungsnummer.
     */
    @Test
    public void pruefeSendungBeiRichtigerEingabe() {
        MockitoAnnotations.initMocks(this);

        // Testdaten vorbereiten
        sendung.setShippingMethod("1");
        sendung.setSubmissionDate(LocalDate.now());
        sendung.setDeliveryDate(LocalDate.now().plusDays(2));

        // Mockingverhalten definieren
        Mockito.when(sendungsRepository.existsById(Integer.valueOf(1))).thenReturn(true);
        Mockito.when(sendungsRepository.findById(1)).thenReturn(Optional.of(sendung));

        // Logik ausführen
        Shipment pruefeSendung = sendungsService.verifyingShipment(sendung);

        // Ergebnis überprüfen
        Assert.assertNotNull(pruefeSendung);
        Assert.assertNotNull(pruefeSendung.getShipmentNumber());
        Assert.assertThat(pruefeSendung.getShipmentNumber(), is(Integer.parseInt(sendung.getShippingMethod())));
    }

    /**
     * Dieser Test prüft das korrekte Verhalten bei Eingabe einer Sendungsnummer, die nicht in der Datenbank vorhanden ist.
     */
    @Test
    public void pruefeSendungBeiFalscherEingabeAlsZahl() {
        MockitoAnnotations.initMocks(this);

        // Testdaten vorbereiten
        sendung.setShippingMethod("899");

        // Mockingverhalten definieren
        Mockito.when(sendungsRepository.existsById(Integer.valueOf(899))).thenReturn(false);

        // Logik ausführen
        Shipment pruefeSendung = sendungsService.verifyingShipment(sendung);

        // Ergebnis überprüfen
        Assert.assertThat(pruefeSendung.getShipmentNumber(), is(nullValue()));
    }

    /**
     * Dieser Test prüft das korrekte Verhalten bei  Eingabe einer falschen Sendungsnummer als Buchstaben.
     */
    @Test
    public void pruefeSendungBeiFalscherEingabeBuchstabe() {
        MockitoAnnotations.initMocks(this);

        // Testdaten vorbereiten
        sendung.setShippingMethod("b");

        // Logik ausführen
        Shipment pruefeSendung = sendungsService.verifyingShipment(sendung);

        // Ergebnis überprüfen
        Assert.assertThat(pruefeSendung.getShipmentNumber(), is(nullValue()));
    }

    /**
     * Dieser prüft ob das Datum richtig formattiert wurde.
     */

    
    @Test
    public void pruefeDatumsFormattierung() {
        MockitoAnnotations.initMocks(this);

        LocalDate localDate1 = LocalDate.of(2019, 7,25);
        LocalDate localDate2 = LocalDate.of(2019, 7,26);

        sendung.setSubmissionDate(localDate1);
        sendung.setDeliveryDate(localDate2);

        sendungsService.formatDate(sendung);

        Assert.assertThat(sendung.getFormattedSubmissionDate(),
                either(containsString("Donnerstag, 25. Juli 2019")).or(containsString("Thursday, 25. July 2019")));
        Assert.assertThat(sendung.getFormattedDeliveryDate(),
                either(containsString("Freitag, 26. Juli 2019")).or(containsString("Friday, 26. July 2019")));
    }
}

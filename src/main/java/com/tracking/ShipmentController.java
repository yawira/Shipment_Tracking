package com.tracking;

import com.tracking.entities.Shipment;
import com.tracking.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This is the controller class of the web application.
 */
@Controller
public class ShipmentController {

    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    /**
     * Displays the landing page and creates a new shipment object in the model.
     */
    @GetMapping(value = "/")
    public String showLandingPage(Model model) {

        model.addAttribute("newShipment", new Shipment());

        return "searchShipment";
    }
    /**
     * Edits the user input via the form.
     * Thymeleaf deliberately enters the shipping method in order to process the user input as a string.
     * @p shipment is created to store the entered transmission number.
     */
    @PostMapping(value = "/searchShipment")
    public String editInput(Model model, @ModelAttribute("newShipment") Shipment shipment) {

        Shipment foundShipment = shipmentService.verifyingShipment(shipment);

        model.addAttribute("foundShipment", foundShipment);

        return "shipmentInfo";
    }
    /**
     * Displays the help page.
     */
    @GetMapping(value = "/help")
    public String displayHelpPage(Model model) {

        model.addAttribute("neueSendung", new Shipment());

        return "help";
    }
}
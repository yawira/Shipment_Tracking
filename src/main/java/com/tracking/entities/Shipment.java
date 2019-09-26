package com.tracking.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Shipment {
    /**
     * Spring creates an automated ID that is used here as the shipment number.
     * The following mapping is used for the database connection via MariaDB:
     * One Customer(shipper/addressee) has many shipments.
     * A Shipment always has only one recipient and one sender.
     * The intersection is the customer ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer shipmentNumber;

    private String shippingMethod;
    private String deliveryStatus;
    private LocalDate submissionDate;
    private LocalDate deliveryDate;

    @Transient
    private String formattedSubmissionDate;
    @Transient
    private String formattedDeliveryDate;

    @ManyToOne
    private Customer shipper;

    @ManyToOne
    private Customer addressee;

    public Shipment() {
    }

    public Shipment(String shippingMethod, String deliveryStatus, LocalDate submissionDate, LocalDate deliveryDate,
                    Customer shipper, Customer addressee) {
        this.shippingMethod = shippingMethod;
        this.deliveryStatus = deliveryStatus;
        this.submissionDate = submissionDate;
        this.deliveryDate = deliveryDate;
        this.shipper = shipper;
        this.addressee = addressee;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setFormattedSubmissionDate(String formattedSubmissionDate) {
        this.formattedSubmissionDate = formattedSubmissionDate;
    }

    public void setFormattedDeliveryDate(String formattedDeliveryDate) {
        this.formattedDeliveryDate = formattedDeliveryDate;
    }

    public String getFormattedSubmissionDate() {
        return formattedSubmissionDate;
    }

    public String getFormattedDeliveryDate() {
        return formattedDeliveryDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public Customer getAddressee() {
        return addressee;
    }

    public void setAddressee(Customer addressee) {
        this.addressee = addressee;
    }

    public Integer getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(Integer shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
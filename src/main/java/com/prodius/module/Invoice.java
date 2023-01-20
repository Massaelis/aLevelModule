package com.prodius.module;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;

@Setter
@Getter
public class Invoice {
    private ArrayList<? extends ProductVariables> productVariables;
    private Customer customer;
    @Getter
    private String type;
    private final int limit;

    public Invoice(final int limit) {
        this.limit = limit;
    }
    public void setType() {
        int sum = productVariables.stream()
                .map(ProductVariables::getPrice)
                .reduce(0, Integer::sum);
        if (sum > limit) {
            type = "Wholesale";
        } else {
            type = "Retail";
        }
    }
    public void setType(final String type) {
        if (!type.isBlank()) {
            this.type = type;
        }
    }
    public void printInvoice() {
        printDataAndTime();
        printCustomer(this.getCustomer());
        System.out.println("Invoice type: " + this.getType());
        printProductVariables(this.getProductVariables());
        System.out.println();
    }
    private void printProductVariables(final ArrayList<? extends ProductVariables> productVariables) {
        if (productVariables == null) {
            System.out.println("Product variables is null");
        } else {
            for (ProductVariables variables : productVariables) {
                if (variables.getType().equals(ProductType.TELEVISION)) {
                    System.out.println("Television: series: " + variables.getSeries() + "; screen type: "
                            + variables.getScreenType() + "; price: " + variables.getPrice() + "; diagonal: "
                            + ((Television) variables).getDiagonal() + "; country: "
                            + ((Television) variables).getCountry());
                } else {
                    System.out.println("Telephone: series: " + variables.getSeries() + "; screen type: "
                            + variables.getScreenType() + "; price - " + variables.getPrice() + "; model: "
                            + ((Telephone) variables).getModel());
                }
            }
        }
    }
    private void printCustomer(final Customer customer) {
        Optional.ofNullable(customer).ifPresentOrElse(print ->
                System.out.println("Customer ID: " + customer.getId() + "; e-mail: " + customer.getEmail() +
                        "; age: " + customer.getAge()), () -> System.out.println("Customer is null"));
    }
    private void printDataAndTime() {
        System.out.println(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
    }
}
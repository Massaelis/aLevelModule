package com.prodius.service;
import com.prodius.module.*;

import java.util.*;

public class ShopService {

    private Random RANDOM = new Random();
    private ArrayList<Invoice> invoices = new ArrayList<>();
    private ArrayList<HashMap<String, String>> csv;

    public ShopService() {
    }
    public ShopService(final ArrayList<HashMap<String, String>> csv) {
        this.csv = csv;
    }
    public ArrayList<? extends ProductVariables> createProductVariables(final int count) {
        ArrayList<ProductVariables> variables = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ProductVariables productVariables = null;
            final HashMap<String, String> product = csv.get(RANDOM.nextInt(0, (csv.size())));
            if (product.get("type").equals("Telephone")) {
                productVariables = new Telephone();
                ((Telephone) productVariables).setModel(product.get("model"));
            } else if (product.get("type").equals("Television")) {
                productVariables = new Television();
                ((Television) productVariables).setDiagonal(Integer.parseInt(product.get("diagonal")));
                ((Television) productVariables).setCountry(product.get("country"));
            }
            productVariables.setSeries(product.get("series"));
            productVariables.setScreenType(product.get("screen type"));
            productVariables.setPrice(Integer.parseInt(product.get("price")));
            variables.add(productVariables);
        }
        return variables;
    }
    public ArrayList<Invoice> createInvoices(final int count) {
        for (int i = 0; i < count; i++) {
            Invoice invoice = new Invoice(5000);
            final PersonService personService = new PersonService();
            invoice.setCustomer(personService.createPerson());
            invoice.setProductVariables(createProductVariables(RANDOM.nextInt(1, 6)));
            invoice.setType();
            invoice.printInvoice();
            invoices.add(invoice);
        }
        return invoices;
    }
}
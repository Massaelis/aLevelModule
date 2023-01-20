package com.prodius.service;

import com.prodius.module.Customer;
import com.prodius.module.ProductType;
import com.prodius.module.ProductVariables;
import com.prodius.module.Invoice;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {
    private ArrayList<Invoice> invoices;

    public AnalyticsService(final ArrayList<Invoice> invoices) {
        this.invoices = invoices;

    }
    public Map<ProductType, Long> quantitySoldProductType() {
        return invoices.stream()
                .flatMap(g -> g.getProductVariables().stream())
                .collect(Collectors.groupingBy(ProductVariables::getType, Collectors.counting()));
    }
    public Map<Integer, Customer> minSumInvoice() {
        return invoices.stream()
                .min(Comparator.comparingInt(invoice -> invoice.getProductVariables()
                        .stream().mapToInt(ProductVariables::getPrice).sum()))
                .stream().collect(Collectors.toMap(invoice -> invoice.getProductVariables()
                                .stream().mapToInt(ProductVariables::getPrice).sum(),
                        Invoice::getCustomer, (key1, key2) -> key1, HashMap::new));
    }
    public void printMinSum (Map<Integer, Customer> minSumInvoice) {
        System.out.print("Min sum: " + minSumInvoice.keySet().toArray()[0]);
        Customer c = minSumInvoice.get(minSumInvoice.keySet().toArray()[0]);
        System.out.println("id: " + c.getId() + "; e-mail: " + c.getEmail());
    }
    public int sumAllSales() {
        return invoices.stream()
                .flatMap(g -> g.getProductVariables().stream())
                .mapToInt(ProductVariables::getPrice)
                .sum();
    }
    public long quantityInvoiceRetail() {
        return invoices.stream()
                .filter(t -> t.getType().equals("retail"))
                .count();
    }
    public List<Invoice> invoicesProductType() {
        ArrayList<Invoice> productType = new ArrayList<>();
        invoices.stream().forEach(invoice -> {
            if (invoice.getProductVariables().stream().allMatch(productVariablesTelevision ->
                    productVariablesTelevision.getType() == ProductType.TELEVISION ||
                            invoice.getProductVariables().stream().allMatch(productVariablesTelephone ->
                                    productVariablesTelephone.getType() == ProductType.TELEPHONE)))
                productType.add(invoice);
        });
        return productType;
    }
    public List<Invoice> firstThreeInvoices() {
        return invoices.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
    public List<Invoice> invoicesCustomerLowAge() {
        return invoices.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .peek(i -> i.setType("Low age"))
                .collect(Collectors.toList());
    }

    public List<Invoice> invoicesSort() {
        Comparator<Invoice> compareByAgeCustomer = Comparator.comparing((invoice -> invoice.getCustomer().getAge()));
        Comparator<Invoice> compareQuantityVariables = Comparator.comparing(invoice -> invoice.getProductVariables().size());
        Comparator<Invoice> compareSum = Comparator.comparing(invoice -> invoice.getProductVariables().stream()
                .mapToInt(ProductVariables::getPrice).sum());
        Comparator<Invoice> compare = compareByAgeCustomer.reversed().thenComparing(compareQuantityVariables)
                .thenComparing(compareSum);
        return invoices.stream()
                .sorted(compare)
                .collect(Collectors.toList());
    }

    public void printAllAnalytics(){
        System.out.println("Total sold product type: " + quantitySoldProductType());
        System.out.println("Min invoice sum and customer: ");
        printMinSum(minSumInvoice());
        System.out.println("Sum all sales: " + sumAllSales());
        System.out.println("Invoices retail: " + quantityInvoiceRetail());
        System.out.println("Invoices with one product type:");
        printListInvoices(invoicesProductType());
        System.out.println("First three invoices:");
        printListInvoices(firstThreeInvoices());
        System.out.println("Customers under 18 years: ");
        printListInvoices(invoicesCustomerLowAge());
        System.out.println("Sorted invoices: ");
        printListInvoices(invoicesSort());
    }
    public void printListInvoices(List<Invoice> invoices) {
        if (invoices.isEmpty() || invoices == null) {
            System.out.println("Invoice null");
        } else {
            for (Invoice invoice : invoices) {
                invoice.printInvoice();
            }
        }
    }
}
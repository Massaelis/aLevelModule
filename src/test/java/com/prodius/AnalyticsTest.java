package com.prodius;

import com.prodius.module.*;
import com.prodius.service.AnalyticsService;
import com.prodius.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsTest {
    private final ArrayList<Invoice> invoices = new ArrayList<>(3);
    private final AnalyticsService target = new AnalyticsService(invoices);

    private final PersonService personService = new PersonService();
    private final Customer customerOne = personService.createPerson();
    private final Customer customerTwo = personService.createPerson();
    private final Customer customerThree = personService.createPerson();
    private final Invoice invoiceOne = new Invoice(450);
    private final Invoice invoiceTwo = new Invoice(450);
    private final Invoice invoiceThree = new Invoice(450);

    @BeforeEach
    void setUp() {
        final ArrayList<Telephone> productVariablesOne = new ArrayList<>(2);
        final ArrayList<Television> productVariablesTwo = new ArrayList<>(1);
        final ArrayList<Television> productVariablesThree = new ArrayList<>(1);
        final Telephone telephoneOne = new Telephone();
        final Television televisionOne = new Television();
        final Telephone telephoneTwo = new Telephone();
        final Television televisionTwo = new Television();
        telephoneOne.setPrice(100);
        telephoneTwo.setPrice(200);
        televisionOne.setPrice(400);
        televisionTwo.setPrice(500);
        customerOne.setAge(16);
        customerTwo.setAge(35);
        customerThree.setAge(35);
        productVariablesOne.add(telephoneOne);
        productVariablesOne.add(telephoneTwo);
        productVariablesTwo.add(televisionOne);
        productVariablesThree.add(televisionTwo);
        invoiceOne.setProductVariables(productVariablesOne);
        invoiceOne.setType();
        invoiceOne.setCustomer(customerOne);
        invoiceTwo.setProductVariables(productVariablesTwo);
        invoiceTwo.setType();

        invoiceTwo.setCustomer(customerTwo);
        invoiceThree.setProductVariables(productVariablesThree);
        invoiceThree.setCustomer(customerThree);
        invoiceThree.setType();
        invoices.add(0,invoiceOne);
        invoices.add(1,invoiceTwo);
        invoices.add(2,invoiceThree);
    }

    @Test
    void quantitySoldGoodsPositive() {
        Map<ProductType, Long> expected = new HashMap<>();
        expected.put(ProductType.TELEVISION, 2L);
        expected.put(ProductType.TELEPHONE, 2L);
        Assertions.assertEquals(expected, target.quantitySoldProductType());
    }

    @Test
    void minSumInvoicePositive() {
        Map<Integer, Customer> expected = new HashMap<>();
        expected.put(300, customerOne);
        Assertions.assertEquals(expected, target.minSumInvoice());
    }
    @Test
    void sumAllSalesPositive() {
        int expected = 1200;
        Assertions.assertEquals(expected, target.sumAllSales());
    }
    @Test
    void invoicesOneProductTypePositive() {
        List<Invoice> expected = new ArrayList<>(3);
        expected.add(invoiceOne);
        expected.add(invoiceTwo);
        expected.add(invoiceThree);
        Assertions.assertEquals(expected, target.invoicesProductType());
    }
    @Test
    void firstThreeInvoicesPositive() {
        List<Invoice> expected = new ArrayList<>(3);
        invoices.add(3, new Invoice(450));
        invoices.add(4, new Invoice(450));
        expected.add(invoiceOne);
        expected.add(invoiceTwo);
        expected.add(invoiceThree);
        Assertions.assertEquals(expected, target.firstThreeInvoices());
    }
    @Test
    void invoicesCustomerLowAgePositive() {
        List<Invoice> expected = new ArrayList<>(1);
        expected.add(invoiceOne);
        Assertions.assertEquals(expected, target.invoicesCustomerLowAge());
    }
    @Test
    void invoicesSortPositive() {
        List<Invoice> expected = new ArrayList<>(3);
        expected.add(0, invoiceTwo);
        expected.add(1, invoiceThree);
        expected.add(2,invoiceOne);
        Assertions.assertEquals(expected, target.invoicesSort());
    }
}
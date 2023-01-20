package com.prodius;

import com.prodius.module.Invoice;
import com.prodius.service.AnalyticsService;
import com.prodius.service.ReadService;
import com.prodius.service.ShopService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ReadService readService = new ReadService();
        ShopService shopService = new ShopService(readService.readFile());
        ArrayList<Invoice> invoices = shopService.createInvoices(15);
        AnalyticsService saleAnalytics = new AnalyticsService(invoices);
        saleAnalytics.printAllAnalytics();
    }
}

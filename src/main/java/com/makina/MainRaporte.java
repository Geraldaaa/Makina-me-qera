package com.makina;

import com.makina.Services.Raporti;
import java.util.Date;


public class MainRaporte {

    public static void main(String[] args) {
        Raporti r = new Raporti();

        Date d = java.sql.Date.valueOf("2025-09-06");

       // System.out.println(r.pagesatSipasDates(d));

        System.out.println(r.pagesatPerCustomer(7));

    }




}

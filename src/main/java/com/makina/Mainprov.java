package com.makina;

import com.makina.Services.InventariService;

public class Mainprov {


    public static void main(String[] args) {
        InventariService is = new InventariService();


        //ky main sherben per proceset ne inventar qe mund te behen pasi ka mbaruar procesi i marrjes me qera te makines

        //kthimi i makines se marre me qera + kalon statusin e makines avaible dhe sastusin e qerase e ben returned
        is.ktheVehicle(13);
        is.ktheVehicle(14);

        //kthen statusin e makines ne paid pasi eshte bere pagesa
        is.markPaymentAsPaid(2);




    }
}

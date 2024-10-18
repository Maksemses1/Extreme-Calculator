package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Logging {

    public void log(double number) {
        Date date = new Date();
        date.setTime(date.getTime() + (1000 * 60 * 60 * 24));
        System.out.println(date + "  ||  " +String.format("%.4f", number));
    }

    public void log(ArrayList<String> arrayList) {
        Date date = new Date();
        String arrayListString = arrayList.toString();
        date.setTime(date.getTime() + (1000 * 60 * 60 * 24));
        System.out.println(date + "  ||  " +arrayListString);
    }

}

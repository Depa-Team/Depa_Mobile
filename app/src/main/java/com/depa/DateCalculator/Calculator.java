package com.depa.DateCalculator;

import java.util.Calendar;

public class Calculator {
    public String getDeathLineDateForMontlyPayment(){
        Calendar cal = Calendar.getInstance();
        int res = cal.getActualMaximum(Calendar.DATE);

        Calendar c1 = Calendar.getInstance();
        int mes = c1.get(Calendar.MONTH)+1;

        String result=String.valueOf(res);
        return result+" de "+ getMesEspañol(mes) ;
    }
    public String getDeathLineForContract(int day,int month){
        Calendar c1 = Calendar.getInstance();

        return day+" de "+ getMesEspañol(month) ;
    }
    public String getMesEspañol(int mes){
        String mesString;
        switch (mes) {
            case 1:  mesString = "Enero";
                break;
            case 2:  mesString  = "Febrero";
                break;
            case 3:  mesString = "Marzo";
                break;
            case 4:  mesString = "Abril";
                break;
            case 5:  mesString = "Mayo";
                break;
            case 6:  mesString = "Junio";
                break;
            case 7:  mesString = "Julio";
                break;
            case 8:  mesString = "Agosto";
                break;
            case 9:  mesString = "Septiembre";
                break;
            case 10: mesString = "Octubre";
                break;
            case 11: mesString = "Noviembre";
                break;
            case 12: mesString = "Diciembre";
                break;
            default: mesString = "Invalid month";
                break;
        }
        return mesString;
    }

    public double getDayPorcentageLeft(int actual){
        Calendar cal = Calendar.getInstance();
        int res = cal.getActualMaximum(Calendar.DATE);

        double total=100 * ((res-actual) / (double)res);
        return 100-total;

    }
    public int getDayLeft(int actual){
        Calendar cal = Calendar.getInstance();
        int res = cal.getActualMaximum(Calendar.DATE);

        int total=res-actual;
        return total;

    }
}

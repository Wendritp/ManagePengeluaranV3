package com.app.ManagePengeluaran.utils;

import java.text.NumberFormat;
import java.util.Locale;
public class FunctionHelper {
    public static String convertRupiah(int nominal){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(nominal);
        //Ngerubah Inputan Jadi RUpiah
    }
}

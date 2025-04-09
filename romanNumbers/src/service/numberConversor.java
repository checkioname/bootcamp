package service;

import java.io.CharArrayReader;
import java.util.Map;

public class numberConversor {
    Map<String, Integer> map = Map.of(
            "I", 1,
            "V", 5,
            "X", 10,
            "L", 50,
            "C", 100,
            "D", 500,
            "M", 1000
    );


    public int toDecimal(String romanNumber) {
        Integer decimalNumber = 0;
        char[] romanNumberArray = romanNumber.toUpperCase().toCharArray();
        for (int i = 0; i <= romanNumber.length(); i++ ) {
            var value = map.get(romanNumberArray[i]);
            if (i == 0) {
                decimalNumber += value;
                continue;
            }

            var lastValue = map.get(romanNumberArray[i -1]);
            if (lastValue < value) {
                decimalNumber -= value;
                continue;
            }

            decimalNumber += value;
        }
        return 1;
    }




}

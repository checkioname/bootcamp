package service;

import java.io.CharArrayReader;
import java.util.Map;

@Service
public class NumberConversor {
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
        for (int i = 0; i < romanNumber.length(); i++ ) {
            int value = map.get(String.valueOf(romanNumberArray[i]));

            if (i > 0 && value > map.get(String.valueOf(romanNumberArray[i - 1]))) {
                decimalNumber += value - 2 * map.get(String.valueOf(romanNumberArray[i - 1]));
            } else {
                decimalNumber += value;
            }
        }
        return decimalNumber;
    }




}

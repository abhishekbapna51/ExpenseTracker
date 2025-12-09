package main.java.com.expensetracker.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate parse(String s) {
        if (s == null || s.trim().isEmpty()) return LocalDate.now();
        try {
            return LocalDate.parse(s.trim(), ISO);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(s.trim(), DateTimeFormatter.ofPattern("d-M-yyyy"));
            } catch (Exception ex) {
                return LocalDate.now();
            }
        }
    }

    public static String format(LocalDate d) {
        return d.format(ISO);
    }
}

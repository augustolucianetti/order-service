package br.com.fiap.orderservice.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String dataAtual(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String text = date.format(formatters);
        return text;
    }

    public static String dataAtualValidadeCartao(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/yyyy");
        String text = date.format(formatters);
        return text;
    }
}

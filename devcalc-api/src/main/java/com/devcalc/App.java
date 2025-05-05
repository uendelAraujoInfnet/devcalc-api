package com.devcalc;

import com.devcalc.service.CalculatorService;
import io.javalin.Javalin;

public class App 
{
    public static void main( String[] args ) {
        CalculatorService service = new CalculatorService();

        Javalin app = Javalin.create().start(7000);

        //Método para chamar o endpoint da adição e já fornecer os valores como parametros
        app.get("/add", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.add(a, b)));
        });

        //Método para chamar o endpoint da subtração e já fornecer os valores como parametros
        app.get("/subtract", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.subtract(a , b)));
        });

        //Método para chamar o endpoint da Multiplicação e já fornecer os valores como parametros
        app.get("/multiply", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.multiply(a, b)));
        });

        //Método para chamar o endpoint da Divisão e já fornecer os valores como parametros
        app.get("/divide", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.divide(a, b)));
        });
    }
}

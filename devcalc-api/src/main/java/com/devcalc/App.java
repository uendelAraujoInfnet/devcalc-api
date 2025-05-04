package com.devcalc;

import com.devcalc.service.CalculatorService;
import io.javalin.Javalin;

public class App 
{
    public static void main( String[] args ) {
        CalculatorService service = new CalculatorService();

        Javalin app = Javalin.create().start(7000);

        app.get("/add", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.add(a, b)));
        });

        app.get("/subtract", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.subtract(a , b)));
        });

        app.get("/multiply", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.multiply(a, b)));
        });

        app.get("/divide", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.result(String.valueOf(service.divide(a, b)));
        });
    }
}

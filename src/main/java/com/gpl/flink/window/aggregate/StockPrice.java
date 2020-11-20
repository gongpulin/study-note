package com.gpl.flink.window.aggregate;

/**
 * @author gongpulin
 * date 2020-09-23
 */
public class StockPrice {
    public String symbol;
    public double price;
    public StockPrice (String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

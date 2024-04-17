package org.hadoop.sales.util;

import java.util.HashMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.hadoop.sales.mapper.MapperTotalProfit;
import org.hadoop.sales.reducer.ReducerTotalProfit;

public class HashOptionParams {
    private static HashMap<String, OptionParams> params;

    static{
        params = new HashMap<>();
        ProfitOptionParams profitPerRegionOption = new ProfitOptionParams(MapperTotalProfit.class, ReducerTotalProfit.class, 
        Text.class, DoubleWritable.class, "Profit per region", "Region");
        params.put("-pr", profitPerRegionOption);

        ProfitOptionParams profitPerCountryOption = new ProfitOptionParams(MapperTotalProfit.class, ReducerTotalProfit.class, 
        Text.class, DoubleWritable.class, "Profit per country", "Country");
        params.put("-pc", profitPerCountryOption);

        ProfitOptionParams profitPerItmeOption = new ProfitOptionParams(MapperTotalProfit.class, ReducerTotalProfit.class, 
        Text.class, DoubleWritable.class, "Profit per item", "ItemType");
        params.put("-pi", profitPerItmeOption);
        
    }

    public static OptionParams getParam(String option) {
        return params.get(option);
    }
}

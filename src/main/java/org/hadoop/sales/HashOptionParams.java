package org.hadoop.sales;

import java.util.HashMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.hadoop.sales.mapper.MapperTotalProfitPerRegion;
import org.hadoop.sales.reducer.ReducerTotalProfitPerRegion;

public class HashOptionParams {
    private static HashMap<String, OptionParams> params;

    static{
        params = new HashMap<>();
        OptionParams profitPerRegionOption = new OptionParams(MapperTotalProfitPerRegion.class,
         ReducerTotalProfitPerRegion.class, Text.class, DoubleWritable.class, "Profit per region");
        params.put("-pr", profitPerRegionOption);
    }

    public static OptionParams getParam(String option) {
        return params.get(option);
    }
}

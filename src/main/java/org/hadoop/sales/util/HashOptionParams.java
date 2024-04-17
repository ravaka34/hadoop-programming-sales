package org.hadoop.sales.util;

import java.util.HashMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.hadoop.sales.mapper.MapperQttTotalPerItem;
import org.hadoop.sales.mapper.MapperTotalProfit;
import org.hadoop.sales.reducer.DoubleSumReducer;

public class HashOptionParams {
    private static HashMap<String, OptionParams> params;

    static{
        params = new HashMap<>();
        ProfitOptionParams profitPerRegionOption = new ProfitOptionParams(MapperTotalProfit.class, DoubleSumReducer.class, 
        Text.class, DoubleWritable.class, "Profit per region", "Region");
        params.put("-pr", profitPerRegionOption);

        ProfitOptionParams profitPerCountryOption = new ProfitOptionParams(MapperTotalProfit.class, DoubleSumReducer.class, 
        Text.class, DoubleWritable.class, "Profit per country", "Country");
        params.put("-pc", profitPerCountryOption);

        ProfitOptionParams profitPerItemOption = new ProfitOptionParams(MapperTotalProfit.class, DoubleSumReducer.class, 
        Text.class, DoubleWritable.class, "Profit per item", "ItemType");
        params.put("-pi", profitPerItemOption);

        ItemOptionParams QttItemOptionOnlineParams = new ItemOptionParams(MapperQttTotalPerItem.class, DoubleSumReducer.class, Text.class,
         DoubleWritable.class, "Online qtt sold per item", "1");
        params.put("-qion", QttItemOptionOnlineParams);

        ItemOptionParams QttItemOptionOfflineParams = new ItemOptionParams(MapperQttTotalPerItem.class, DoubleSumReducer.class, Text.class,
         DoubleWritable.class, "Offline qtt sold per item", "0");
        params.put("-qioff", QttItemOptionOfflineParams);
        
    }

    public static OptionParams getParam(String option) {
        return params.get(option);
    }
}

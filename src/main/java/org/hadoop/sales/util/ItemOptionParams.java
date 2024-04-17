package org.hadoop.sales.util;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ItemOptionParams extends OptionParams {

    private String isOnline;

    public ItemOptionParams(Class<? extends Mapper> mapperClass, Class<? extends Reducer> reducerClass,
            Class<?> outputKeyClass, Class<?> outputValueClass, String description, String isOnline) {
        super(mapperClass, reducerClass, outputKeyClass, outputValueClass, description);
        this.isOnline = isOnline;
    }

    public String getIsOnline() {
        return isOnline;
    }

    

    
}

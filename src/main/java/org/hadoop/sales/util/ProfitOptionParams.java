package org.hadoop.sales.util;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ProfitOptionParams extends OptionParams {

    private String groupBy;

    public ProfitOptionParams(Class<? extends Mapper> mapperClass, Class<? extends Reducer> reducerClass,
            Class<?> outputKeyClass, Class<?> outputValueClass, String description, String groupBy) {
        super(mapperClass, reducerClass, outputKeyClass, outputValueClass, description);
        this.groupBy = groupBy;
    }

    public String getGroupBy() {
        return groupBy;
    }
}

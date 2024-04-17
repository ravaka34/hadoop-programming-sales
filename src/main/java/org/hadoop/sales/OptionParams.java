package org.hadoop.sales;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class OptionParams {

    Class<? extends Mapper>  mapperClass;
    Class<? extends Reducer> reducerClass;
    Class<?> outputKeyClass;
    Class<?> outputValueClass;
    String description;

    public OptionParams(Class<? extends Mapper> mapperClass, Class<? extends Reducer> reducerClass, Class<?> outputKeyClass,
            Class<?> outputValueClass, String description) {
        this.mapperClass = mapperClass;
        this.reducerClass = reducerClass;
        this.outputKeyClass = outputKeyClass;
        this.outputValueClass = outputValueClass;
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public Class<? extends Mapper> getMapperClass() {
        return mapperClass;
    }
  
    public Class<? extends Reducer> getReducerClass() {
        return reducerClass;
    }
   
    public Class<?> getOutputKeyClass() {
        return outputKeyClass;
    }
    
    public Class<?> getOutputValueClass() {
        return outputValueClass;
    }
}

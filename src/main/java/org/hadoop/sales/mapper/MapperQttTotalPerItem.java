package org.hadoop.sales.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.hadoop.sales.util.CSVSalesLineReader;

public class MapperQttTotalPerItem extends Mapper<Object, Text, Text, DoubleWritable> {

    @Override
    protected void map(Object keyIn, Text valueIn, Mapper<Object, Text, Text, DoubleWritable>.Context context)
            throws IOException, InterruptedException {
        CSVSalesLineReader csv = new CSVSalesLineReader(valueIn);
        if((csv.getValue("SalesChannel").equals("Online") && context.getConfiguration().get("getOnline").equals("1"))
         || (csv.getValue("SalesChannel").equals("Offline") && context.getConfiguration().get("getOnline").equals("0"))){
            Text key = new Text(csv.getValue("ItemType"));
            DoubleWritable value = new DoubleWritable (Double.parseDouble(csv.getValue("UnitsSold")));
            context.write(key, value);
        }
    }

    
    
}

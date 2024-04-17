package org.hadoop.sales.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.hadoop.sales.util.CSVSalesLineReader;

public class MapperTotalProfit extends Mapper<Object, Text, Text, DoubleWritable> {

    @Override
    protected void map(Object keyIn, Text valueIn, Mapper<Object, Text, Text, DoubleWritable>.Context context)
            throws IOException, InterruptedException {
        CSVSalesLineReader csv = new CSVSalesLineReader(valueIn);
        Text key = new Text(csv.getValue(context.getConfiguration().get("groupBy")));
        DoubleWritable value = new DoubleWritable(Double.parseDouble(csv.getValue("TotalProfit")));
        context.write(key, value);
    }

    
    
}

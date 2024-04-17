package org.hadoop.sales.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.hadoop.sales.util.CSVSalesLineReader;

public class MapperTotalProfitPerRegion extends Mapper<Object, Text, Text, DoubleWritable > {

    @Override
    protected void map(Object keyIn, Text line, Mapper<Object, Text, Text, DoubleWritable>.Context context)
            throws IOException, InterruptedException {
        // Parse the CSV Line 
        CSVSalesLineReader csv = new CSVSalesLineReader(line);
        //Set the region as a key
        Text key = new Text(csv.getValue("Region"));
        //Set the total profit as a value
        DoubleWritable value = new DoubleWritable(Double.parseDouble(csv.getValue("TotalProfit")));
        context.write(key, value);
    }
    

}

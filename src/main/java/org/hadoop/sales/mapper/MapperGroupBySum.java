package org.hadoop.sales.mapper;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.hadoop.sales.util.CSVSalesLineReader;

public class MapperGroupBySum extends Mapper<Object, Text, Text, DoubleWritable> {

    @Override
    protected void map(Object keyIn, Text valueIn, Mapper<Object, Text, Text, DoubleWritable>.Context context)
            throws IOException, InterruptedException {
        CSVSalesLineReader csv = new CSVSalesLineReader(valueIn);
        Configuration conf = context.getConfiguration();
        if((csv.getValue("SalesChannel").equals("Online") && conf.get("salesChannel").equals("-on"))
         || (csv.getValue("SalesChannel").equals("Offline") && conf.get("salesChannel").equals("-off"))
         || conf.get("salesChannel").equals("-1")){
            Text key = new Text(csv.getValue(conf.get("groupBy")));
            DoubleWritable value = new DoubleWritable (Double.parseDouble(csv.getValue(conf.get("actionOn"))));
            context.write(key, value);
        }
    }

    
    
}

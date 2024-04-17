package org.hadoop.sales.reducer;


import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerTotalProfitPerRegion extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    protected void reduce(Text keyIn, Iterable<DoubleWritable> valuesIn,
            Context context)
            throws IOException, InterruptedException {
        // Sum the totalProfit
        double sum = 0.0;
        for (DoubleWritable doubleWritable : valuesIn) {
            sum += doubleWritable.get();   
        }
        context.write(keyIn, new DoubleWritable(sum));
        
    }
    
}

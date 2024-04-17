package org.hadoop.sales.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerTotalProfit extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values,
            Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context)
            throws IOException, InterruptedException {
        double sum = 0.0;
        for (DoubleWritable doubleWritable : values) {
            sum += doubleWritable.get();
        }
        context.write(key, new DoubleWritable(sum));
    }

    

}

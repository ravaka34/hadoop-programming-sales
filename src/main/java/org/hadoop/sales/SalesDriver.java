package org.hadoop.sales;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import org.hadoop.sales.mapper.MapperGroupBySum;
import org.hadoop.sales.reducer.DoubleSumReducer;

public final class SalesDriver {

    static String actionOn = "";
    static String groupBy = "";
    static String salesChannel = "-1";
    static String fileInput = "";
    static String directoryOutput = "";
    

    private SalesDriver() {
    }

    private static void initParam(String[] ourArgs){
        actionOn = ourArgs[0];
        groupBy = ourArgs[1];
        if (ourArgs.length == 5){
            salesChannel = ourArgs[2];
        }
        fileInput = ourArgs[ourArgs.length - 2];
        directoryOutput = ourArgs[ourArgs.length - 1];
    }


    //[actionOn] [groupBy] [salesChannel(-on or -off)]
  
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        initParam(new GenericOptionsParser(conf, args).getRemainingArgs());
       
        conf.set("groupBy", groupBy);
        conf.set("actionOn", actionOn);
        conf.set("salesChannel", salesChannel);
        
        Job job = Job.getInstance(conf, "Sales count");
        System.out.println("Calculate "+ actionOn+" on "+groupBy);
        if(salesChannel != null){
            System.out.println("SalesChannel = "+salesChannel);
        }
       
        // Group by sum
        // Define the driver, mapper and reducer
        job.setJarByClass(SalesDriver.class);
        job.setMapperClass(MapperGroupBySum.class);
        job.setReducerClass(DoubleSumReducer.class);

        //Define key/value types
        job.setOutputKeyClass(org.apache.hadoop.io.Text.class);
        job.setOutputValueClass(DoubleWritable.class); 

        //Define the input file and the output directory through the args given by the client
        FileInputFormat.addInputPath(job, new Path(fileInput));
        FileOutputFormat.setOutputPath(job, new Path(directoryOutput));

        //Start the hadoop task return 0 if done correctly otherwise return -1
        if(job.waitForCompletion(true)){
            System.exit(0);
        }else{
            System.exit(-1);
        }
    
    }
}

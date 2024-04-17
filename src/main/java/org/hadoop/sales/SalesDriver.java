package org.hadoop.sales;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Hello world!
 */
public final class SalesDriver {
    private SalesDriver() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] ourArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = Job.getInstance(conf, "Sales count");
        String option = ourArgs[0];
        OptionParams optionParams = HashOptionParams.getParam(option);

        System.out.println("Calculate "+optionParams.getDescription());
        
        // Define the driver, mapper and reducer
        job.setJarByClass(SalesDriver.class);
        // Total profit per region
       
        job.setMapperClass(optionParams.getMapperClass());
        job.setReducerClass(optionParams.getReducerClass());

        //Define key/value types
        job.setOutputKeyClass(optionParams.getOutputKeyClass());
        job.setOutputValueClass(optionParams.getOutputValueClass()); 

        //Define the input file and the output directory through the args given by the client
        FileInputFormat.addInputPath(job, new Path(ourArgs[1]));
        FileOutputFormat.setOutputPath(job, new Path(ourArgs[2]));

        //Start the hadoop task return 0 if done correctly otherwise return -1
        if(job.waitForCompletion(true)){
            System.exit(0);
        }else{
            System.exit(-1);
        }
    
    }
}

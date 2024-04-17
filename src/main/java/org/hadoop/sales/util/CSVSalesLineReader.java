package org.hadoop.sales.util;

import java.util.HashMap;

import org.apache.hadoop.io.Text;


public class CSVSalesLineReader {

    private HashMap<String, String> values = new HashMap<>();
    private String[] keys =new String []{"Region", "Country", "ItemType", "SalesChannel", "OrderPriority", "OrderDate",
        "OrderID",	"ShipDate",	"UnitsSold", "UnitPrice",	"UnitCost",	
        "TotalRevenue",	"TotalCost", "TotalProfit"};

    public CSVSalesLineReader(Text CSVLine){
        this.initValues(CSVLine.toString().split(","));
    }

    private void initValues(String[] splits){
        for (int i = 0; i < splits.length; i++) {
            this.values.put(keys[i], splits[i]);
        }
    }

    public String getValue (String key){
        return values.get(key);
    }
}

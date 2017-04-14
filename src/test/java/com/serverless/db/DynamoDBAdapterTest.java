package com.serverless.db;

import com.serverless.data.Transaction;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.After;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mpandit on 4/13/17.
 */
public class DynamoDBAdapterTest extends TestCase {

    private final Logger logger = Logger.getLogger(this.getClass());

    public void testCRUD(){
        try {
            DynamoDBAdapter adapter = DynamoDBAdapter.getInstance();
            adapter.createTransationsTable();
            for (int i = 0; i < 10; i++) {
                Transaction t = new Transaction();
                t.setAccount_id("1234");
                t.setTransaction_id(UUID.randomUUID().toString());
                t.setTransaction_date(new Date(System.currentTimeMillis()));
                t.setAmount(new Random(100).nextFloat());
                adapter.putTransaction(t);
            }
            //now we read
            List<Transaction> results = adapter.getTransactions("1234");
            logger.info("Got " + results.size() + " transactions!");
            assertTrue(results.size()>0);
        }catch(Exception e){
            fail();
        }
    }

}

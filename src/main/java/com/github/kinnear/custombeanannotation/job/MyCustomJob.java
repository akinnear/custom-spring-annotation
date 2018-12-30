package com.github.kinnear.custombeanannotation.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCustomJob implements Job<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(MyCustomJob.class);

    @Override
    public String run(String input) {
        if (input == null) {
            return null;
        }
        String result = new StringBuilder(input).reverse().toString();
        logger.debug("Converted "+input+" to "+result);
        return result;
    }
}

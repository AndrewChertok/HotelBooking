package com.hotelbooking;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {

    public static Long intToLong(Integer value){
        return Long.valueOf(value);
    }

    public static BigDecimal toBigDecimal(Integer value){
        return BigDecimal.valueOf(value);
    }

    public static Date toDate(String value){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return formatter.parse(value);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }


}

package com.hotelbooking.repository;


import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:db/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            log.info(result + " ms\n");
        }
    };


    @AfterClass
    public static void printResult() {
        log.info("\n-------------------------------------------------------------------------------------------------------" +
                "\nTest                                                                                       Duration, ms" +
                "\n-------------------------------------------------------------------------------------------------------\n" +
                results +
                "-------------------------------------------------------------------------------------------------------\n");
        results.setLength(0);
    }

}

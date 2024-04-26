package runner;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit4.Karate;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

//@RunWith(Karate.class)
//@KarateOptions  (features = "classpath:src/test/java/feature/API")
//public class KarateTestRunner {
//    // This class doesn't need any code inside it.
//}

//public class KarateTestRunner {
//

public class KarateTestRunner {

    @Test
    public void testKarate() {
        Results results = Runner.path("classpath:feature/API")
                //.tags("@testPOST")
                .parallel(5);
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }
}
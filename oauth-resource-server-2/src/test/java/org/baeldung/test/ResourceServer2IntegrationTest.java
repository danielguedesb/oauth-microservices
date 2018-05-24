package org.baeldung.test;

import org.baeldung.ResourceServerApplication2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResourceServerApplication2.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResourceServer2IntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {

    }
}

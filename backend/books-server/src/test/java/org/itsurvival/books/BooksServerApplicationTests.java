package org.itsurvival.books;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BooksServerApplication.class)
@WebAppConfiguration
public class BooksServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}

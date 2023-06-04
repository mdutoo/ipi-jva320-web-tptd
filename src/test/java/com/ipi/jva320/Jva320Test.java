package com.ipi.jva320;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.DataInitService;
import com.ipi.jva320.service.SalarieAideADomicileService;

@SpringBootTest
public class Jva320Test {

	@Autowired
	private DataInitService dataInitService;

	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	
	@Test
	private void testinit( ) throws Exception {
		this.dataInitService.run();
		
		this.salarieAideADomicileService.creerSalarieAideADomicile(
                new SalarieAideADomicile("Jean", LocalDate.parse("2022-12-05"), LocalDate.parse("2022-12-05"),
                        20, 0,
                        80, 10, 1));
	}
	
}

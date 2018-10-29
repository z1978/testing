package com.testing.batch.job.first;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(BatchConstants.FIRST_JOB_ITEM_PROCESSOR_ID)
public class FirstItemProcessor implements ItemProcessor<PersonDto, Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstItemProcessor.class);
	
	@Autowired
	private PersonService personService;

	private static final DateFormat DF = new SimpleDateFormat(
      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	
	public Person process(PersonDto item) throws Exception {
		
//		List<Person> list = personService.getAll();
		
//		System.out.println(list.size());

		final String firstName = item.getFirstName().toUpperCase();
		final String lastName = item.getLastName().toUpperCase();

		final Person transformed = new Person(firstName, lastName);
//		System.out.println("==================================================");
//    System.out.println("Processor time = [" + DF.format(new Date()) + "]");
//    System.out.println("==================================================");
//		LOGGER.info("Converting (" + item + ") into (" + transformed + ")");

		return transformed;
	}
}
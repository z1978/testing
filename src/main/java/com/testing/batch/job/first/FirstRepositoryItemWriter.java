package com.testing.batch.job.first;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(BatchConstants.FIRST_JOB_REPOSITORY_ITEM_WRITER_ID)
public class FirstRepositoryItemWriter implements ItemWriter<Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstRepositoryItemWriter.class);

	@Autowired
	private PersonService personService;

	public void write(List<? extends Person> items) throws Exception {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Writing to JPA with {} items.", items.size());
		}

		if (!items.isEmpty()) {
			List<Person> list = this.personService.getAll();
			System.out.println(list.size());
			System.out.println("==========");
			
			for (Person item : list) {
				System.out.println(item.getPersonId());
				System.out.println(item.getFirstName());
				System.out.println(item.getLastName());
				personService.createOne(item);
//				item.setFirstName("UUU");
//				personService.saveAndFlush(item);
			}
			
//			this.personService.deleteAll();
			List<Person> list2 = this.personService.getAll();
			System.out.println(list2.size());
			System.out.println("==========");
//			long persistCount = 0;
//			long mergeCount = 0;
//
//			for (Person item : items) {
//				if (item.getId() == null) {
//					entityManager.persist(item);
//					persistCount++;
//				} else {
//					entityManager.merge(item);
//					mergeCount++;
//				}
//			}
//			entityManager.flush();
//			entityManager.clear();
//
//			if (LOGGER.isInfoEnabled()) {
//				LOGGER.info("{} entities persisted.", persistCount);
//				LOGGER.info("{} entities merged.", mergeCount);
//			}
		}
	}
}

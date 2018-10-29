package com.testing.batch.job.first;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(BatchConstants.FIRST_JOB_ITEM_WRITER_ID)
public class FirstItemWriter implements ItemWriter<Person> {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(FirstItemWriter.class);

  private static final DateFormat DF = new SimpleDateFormat(
      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  @Autowired
  private PersonService personService;

  public void write(List<? extends Person> items) throws Exception {
    long startTime = System.currentTimeMillis();
    System.out.println(
        "==================================================FirstItemWriter START");
    System.out.println("Start time = [" + DF.format(new Date()) + "]");
    System.out.println("==================================================");
    LOGGER.info("Start time = [" + DF.format(new Date()) + "]");

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Writing to JPA with {} items.", items.size());
    }

    if (!items.isEmpty()) {
      System.out.println("==================================================");
      System.out.println(items.size());
      System.out.println("==================================================");
      personService.saveAll(items);

      // rollback
//      Person person2 = items.get(0);
//      personService.addOne(person2);
    }

    LOGGER.info("End time = [" + DF.format(new Date()) + "]");
    long endTime = System.currentTimeMillis();
    System.out.println("==================================================");
    LOGGER.info("Program running time = [" + (endTime - startTime) + "]ms");
    System.out.println(
        "Program running time = [" + (endTime - startTime) / 1000f + "]s");
    System.out.println(
        "==================================================FirstItemWriter END");
    LOGGER.info(
        "Program running time = [" + (endTime - startTime) / 1000f + "]s");

    // Delete all
    // personService.delAll();

    // List<Person> list = this.personService.getAll();
    // System.out.println(list.size());
    // System.out.println("==========");
    // for (Person item : items) {
    // try {
    // Person p = new Person();
    // p.setFirstName(item.getFirstName());
    // p.setLastName(item.getLastName());
    // personService.createOne(p);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // for (Person item : list) {
    // System.out.println(item.getPersonId());
    // System.out.println(item.getFirstName());
    // System.out.println(item.getLastName());
    // personService.createOne(item);
    // // item.setFirstName("UUU");
    // // personService.saveAndFlush(item);
    // }

    // this.personService.deleteAll();
    // List<Person> list2 = personService.findAllByPersonIdDesc();
    // List<Person> list2 = this.personService.getAll();
    // System.out.println(list2.size());
    // System.out.println(list2.get(0).getPersonId());
    // System.out.println("==========");
    // long persistCount = 0;
    // long mergeCount = 0;
    //
    // for (Person item : items) {
    // if (item.getId() == null) {
    // entityManager.persist(item);
    // persistCount++;
    // } else {
    // entityManager.merge(item);
    // mergeCount++;
    // }
    // }
    // entityManager.flush();
    // entityManager.clear();
    //
    // if (LOGGER.isInfoEnabled()) {
    // LOGGER.info("{} entities persisted.", persistCount);
    // LOGGER.info("{} entities merged.", mergeCount);
    // }
  }
}

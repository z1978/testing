package com.testing.batch.job.first;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class FirstJobConfiguration {

	@Value("${first.chunck.size}")
	protected Integer chunckSize;

	@Autowired
	protected JobBuilderFactory jobBuilderFactory;

	@Autowired
	protected StepBuilderFactory stepBuilderFactory;

	@Autowired
	protected EntityManagerFactory entityManagerFactory;

	@Autowired
	protected JobRepository jobRepository;

	@Resource(name=BatchConstants.FIRST_JOB_ITEM_READER_ID)
	protected ItemReader<PersonDto> reader;

	@Resource(name=BatchConstants.FIRST_JOB_ITEM_PROCESSOR_ID)
	protected ItemProcessor<PersonDto, Person> processor;

	@Resource(name=BatchConstants.FIRST_JOB_ITEM_WRITER_ID)
	protected ItemWriter<Person> writer;

	@Resource(name=BatchConstants.FIRST_JOB_EXECUTION_LISTENER_ID)
	protected JobExecutionListener listener;
	
	@Autowired
	private PersonRepository personRepository;

	// TODO
	@Bean
	public Job firstJob(@Qualifier("firstStep") Step firstStep) throws Exception {
		return jobBuilderFactory.get(BatchConstants.FIRST_JOB_ID)
				.repository(jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(firstStep)
				.end()
				.build();
	}

	@Bean
	public Step firstStep() throws Exception {
		return stepBuilderFactory.get(BatchConstants.FIRST_JOB_STEP_ID)
				.repository(jobRepository)
				.<PersonDto, Person> chunk(chunckSize)
				.reader(reader)
				.processor(processor)
				.writer(writer)
//				.writer(writer2())
				.build();
	}

	@Bean
	public RepositoryItemWriter<Person> writer2() {
	    RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
	    writer.setRepository(personRepository);
	    writer.setMethodName("save");
	    return writer;
	}

}

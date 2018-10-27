package com.testing.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.testing.batch.model.RecordSO;
import com.testing.batch.model.WriterSO;

public class RecordProcessor implements ItemProcessor<RecordSO, WriterSO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordProcessor.class);

    @Override
    public WriterSO process(final RecordSO item) throws Exception {
        LOGGER.info("Processing Record: {}", item);
        WriterSO writerSo = new WriterSO();
        writerSo.setId(item.getId());
        writerSo.setFullName(item.getFirstName() + " " + item.getLastName());
        writerSo.setRandomNum(String.valueOf(Math.random()).substring(3, 8));
        LOGGER.info("Processed Writer: {}", writerSo);
        return writerSo;
    }
}


package com.zac.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/batch-context.xml", "/test-context.xml" })
public class SampleTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testStep() throws Exception {
        // 単独のStepの実行
        jobLauncherTestUtils.launchStep("sampleStep");
    }

    @Test
    public void testJob() throws Exception {
        // Jobの実行 もちろんパラメータ設定もできる
        jobLauncherTestUtils.launchJob(new JobParameters());
    }
}

package com.github.kinnear.custombeanannotation.config;

import com.github.kinnear.custombeanannotation.annotation.Job;
import com.github.kinnear.custombeanannotation.annotation.JobRunner;
import com.github.kinnear.custombeanannotation.job.MyCustomJob;
import com.github.kinnear.custombeanannotation.runner.SimpleJobRunner;
import com.github.kinnear.custombeanannotation.runner.SimpleListJobRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfiguration {
    @JobRunner
    public SimpleJobRunner simpleJobRunner() {
        return new SimpleJobRunner();
    }

    @JobRunner
    public SimpleListJobRunner simpleListJobRunner() {
        return new SimpleListJobRunner();
    }

    @Job(runnerBean = "simpleJobRunner")
    @Bean
    public MyCustomJob myCustomJob() {
        return new MyCustomJob();
    }

    @Job(runnerBean = "simpleListJobRunner")
    @Bean
    public MyCustomJob myCustomListJob() {
        return new MyCustomJob();
    }
}

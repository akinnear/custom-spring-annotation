package com.github.kinnear.custombeanannotation.runner;

import com.github.kinnear.custombeanannotation.job.Job;

import java.util.function.Supplier;

public abstract class AbstractJobRunner  <JobType extends Job<JobInput, JobOutput>, JobInput, JobOutput, Input, Output>
    implements JobRunner<JobType, JobInput, JobOutput, Input, Output> {

    protected Supplier<JobType> jobSupplier;

    @Override
    public void setup(Supplier<JobType> jobSupplier) {
        this.jobSupplier = jobSupplier;
    }
}

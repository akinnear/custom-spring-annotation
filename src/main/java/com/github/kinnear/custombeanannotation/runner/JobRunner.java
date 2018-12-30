package com.github.kinnear.custombeanannotation.runner;

import com.github.kinnear.custombeanannotation.job.Job;

import java.util.function.Supplier;

public interface JobRunner <JobType extends Job<JobInput, JobOutput>, JobInput, JobOutput, Input, Output> {
    Output run(Input input);
    void setup(Supplier<JobType> jobSupplier);
}

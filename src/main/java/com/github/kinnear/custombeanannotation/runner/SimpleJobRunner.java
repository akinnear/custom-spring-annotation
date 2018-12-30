package com.github.kinnear.custombeanannotation.runner;

import com.github.kinnear.custombeanannotation.job.Job;

public class SimpleJobRunner <JobType extends Job<Input, Output>, Input, Output>
        extends AbstractJobRunner<JobType, Input, Output, Input, Output> {
    @Override
    public Output run(Input input) {
        return jobSupplier.get().run(input);
    }
}

package com.github.kinnear.custombeanannotation.runner;

import com.github.kinnear.custombeanannotation.job.Job;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleListJobRunner <JobType extends Job<Input, Output>, Input, Output>
        extends AbstractJobRunner<JobType, Input, Output, List<Input>, List<Output>> {

    @Override
    public List<Output> run(List<Input> inputs) {
        return inputs.stream()
                .map(value -> jobSupplier.get().run(value))
                .collect(Collectors.toList());
    }
}

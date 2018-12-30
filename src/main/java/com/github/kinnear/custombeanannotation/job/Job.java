package com.github.kinnear.custombeanannotation.job;

public interface Job <Input, Output> {
    Output run(Input input);
}

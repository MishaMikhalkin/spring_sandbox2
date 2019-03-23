package com.m2n.springexample.questionnaire.domain;

import lab1.domain.Result;
import lab1.runner.RunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@ShellComponent
public class ConsoleCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleCommand.class);


    private final RunnerService inquirerService;


    @Autowired
    public ConsoleCommand(RunnerService runnerService) {
        inquirerService = runnerService;
    }

    @ShellMethod("quiz")
    public String quiz(String name) throws Exception {
            Result result = inquirerService.makeInquirer(name);
            inquirerService.calculateScore(result);
            return result.getScore();
    }
}

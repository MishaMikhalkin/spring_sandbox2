package com.m2n.springexample.questionnaire.cmdRunner;

import lab1.domain.Result;
import lab1.runner.RunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class PreparationDev implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationDev.class);

    private final RunnerService inquirerService;

    public PreparationDev(RunnerService inquirerService) {
        this.inquirerService = inquirerService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("DEV mode!!! Что-то настравиваем и подготавливаем, параметры: {} ", Arrays.toString(args));
        String questionnaierName = "name";
        if (args.length == 1) {
            questionnaierName = args[0];
        }

        try {
            Result result = inquirerService.makeInquirer(questionnaierName);
            inquirerService.printResult(result);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }
}

package lab1_config.config;

import lab1.App;
import lab1.dao.QuestionnaireCSVDao;
import lab1.dao.QuestionnaireDao;
import lab1.dao.StudentConsoleDao;
import lab1.dao.StudentDao;
import lab1.runner.RunnerService;
import lab1.runner.RunnerServiceImpl;
import lab1.service.*;
import lab1.util.AppLanguage;
import lab1.util.ConsoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ConditionalOnClass(App.class)
@EnableConfigurationProperties(YamlProps.class)
public class GenericConfigAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GenericConfigAutoConfiguration.class);

    private final YamlProps props;

    public GenericConfigAutoConfiguration(YamlProps props) {
        this.props = props;
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public MessageSource applicationProperties() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:application");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

    @Bean
    @ConditionalOnMissingBean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public AppLanguage locale() {
        return new AppLanguage(props.getLanguage());
    }


    @Bean
    @ConditionalOnMissingBean
    public ConsoleUtil consoleUtil(AppLanguage locale, MessageSource messageSource) {
        return  new ConsoleUtil(locale, messageSource);
    }


    @Bean
    @ConditionalOnMissingBean
    public StudentDao studentDao(ConsoleUtil consoleUtil) { return new StudentConsoleDao(consoleUtil); }


    @Bean
    @ConditionalOnMissingBean
    public QuestionnaireDao questionnaireDao(AppLanguage appLanguage, ConsoleUtil consoleUtil) {
        return new QuestionnaireCSVDao(props.questionFilePrefix, props.answerFilePrefix, props.fileNameFormat,
                appLanguage, consoleUtil); }

    @Bean
    @ConditionalOnMissingBean
    public StudentService studentService(StudentDao studentDao) { return new StudentServiceImpl(studentDao); }

    @Bean
    @ConditionalOnMissingBean
    public QuestionnaireService questionnaireService(QuestionnaireDao questionnaireDao) { return  new QuestionnaireServiceImpl(questionnaireDao); }

    @Bean
    @ConditionalOnMissingBean
    public RunnerService inquirerService(StudentService studentService,
                                         QuestionnaireService questionnaireService,
                                         ScoringService scoringService,
                                         ConsoleUtil consoleUtil) {
        return new RunnerServiceImpl(studentService, questionnaireService, scoringService, consoleUtil); }

    @Bean
    @ConditionalOnMissingBean
    public  ScoringService scoringService() { return  new ScoringServiceImpl(); }

}

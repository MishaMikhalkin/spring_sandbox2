package lab1_config.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="lab1")
public class YamlProps {

    public String questionFilePrefix;
    public String answerFilePrefix;
    public String fileNameFormat;
    public String language;

    public String getQuestionFilePrefix() {
        return questionFilePrefix;
    }

    public void setQuestionFilePrefix(String questionFilePrefix) {
        this.questionFilePrefix = questionFilePrefix;
    }

    public String getAnswerFilePrefix() {
        return answerFilePrefix;
    }

    public void setAnswerFilePrefix(String answerFilePrefix) {
        this.answerFilePrefix = answerFilePrefix;
    }

    public String getFileNameFormat() {
        return fileNameFormat;
    }

    public void setFileNameFormat(String fileNameFormat) {
        this.fileNameFormat = fileNameFormat;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

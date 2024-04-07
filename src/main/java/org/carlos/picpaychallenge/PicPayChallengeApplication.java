package org.carlos.picpaychallenge;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableJdbcAuditing
public class PicPayChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(PicPayChallengeApplication.class, args);
  }

  @Bean
  NewTopic notificationTopic() {
    return TopicBuilder.name("transaction-notification")
            .build();
  }
}

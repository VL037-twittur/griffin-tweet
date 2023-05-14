package vincentlow.twittur.tweet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"vincentlow.twittur.tweet.*"})
@EntityScan(basePackages = {"vincentlow.twittur.tweet.*"})
@EnableFeignClients(basePackages = {"vincentlow.twittur.tweet.*"})
@EnableMongoRepositories(basePackages = {"vincentlow.twittur.tweet.*"})
public class GriffinTweetApplication {

  public static void main(String[] args) {

    SpringApplication.run(GriffinTweetApplication.class);
  }
}

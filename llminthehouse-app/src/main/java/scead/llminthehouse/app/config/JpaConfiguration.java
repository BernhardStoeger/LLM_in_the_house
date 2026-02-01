package scead.llminthehouse.app.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import scead.llminthehouse.llminthehouseApplication;

@Configuration
@EnableJpaRepositories(basePackageClasses = llminthehouseApplication.class)
@EntityScan(basePackageClasses = llminthehouseApplication.class)
class JpaConfiguration
{
}

package scead.llminthehouse.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class JSONFileInitializer<T> extends BaseInitializer
{
    @Autowired
    protected JpaRepository<T, Long> repository;

    private final String relativePath;

    private final boolean skipImportIfNotEmpty;

    protected JSONFileInitializer(String relativePath)
    {
        this.relativePath = relativePath;
        this.skipImportIfNotEmpty = true;
    }

    protected JSONFileInitializer(String relativePath, boolean skipImportIfNotEmpty)
    {
        this.relativePath = relativePath;
        this.skipImportIfNotEmpty = skipImportIfNotEmpty;
    }

    protected String getRelativePath()
    {
        return relativePath;
    }

    @Autowired
    public void setRepository(JpaRepository<T, Long> repository)
    {
        this.repository = repository;
    }

    @Override
    public void addData()
    {
        Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(getClass(), JSONFileInitializer.class);
        List<T> entities = loadEntities(Objects.requireNonNull(classes)[0]);
        repository.saveAll(entities);
    }

    protected List<T> loadEntities(Class<?> classe)
    {
        if(repository.findAll().isEmpty() || !skipImportIfNotEmpty)
        {
            try
            {
                log.info("No initializer record for entities of type {} found in the database. Start import from {}",
                    classe.getSimpleName(), getRelativePath());
                InputStream inputStream = this.getClass().getResourceAsStream(getRelativePath());
                ObjectMapper objectMapper = new ObjectMapper();
                configureDeserializer(objectMapper);

                JavaType parametricListType =
                    objectMapper.getTypeFactory().constructParametricType(List.class, classe);

                return objectMapper.readValue(inputStream, parametricListType);
            }
            catch (IOException e)
            {
                log.error("could not parse {}, error: {}", getRelativePath(), e.getMessage());
            }
        }
        else
        {
            log.info("Entities of type {} found in the database. Skipping import from json", classe.getSimpleName());
        }
        return Collections.emptyList();
    }

    protected List<T> loadEntitiesWithoutSkipImport(Class<?> classe)
    {
        try
        {
            log.info("No initializer record for entities of type {} found in the database. Start import from {}",
                classe.getSimpleName(), getRelativePath());
            InputStream inputStream = this.getClass().getResourceAsStream(getRelativePath());
            ObjectMapper objectMapper = new ObjectMapper();
            configureDeserializer(objectMapper);

            JavaType parametricListType =
                objectMapper.getTypeFactory().constructParametricType(List.class, classe);

            return objectMapper.readValue(inputStream, parametricListType);
        }
        catch (IOException e)
        {
            log.error("could not parse {}, error: {}", getRelativePath(), e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    protected void removeData()
    {
    }

    protected void configureDeserializer(ObjectMapper objectMapper)
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_DATE));
        objectMapper.registerModule(timeModule);
    }
}

package scead.llminthehouse.base;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseInitializer
{
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init()
    {
        log.info("{}  => Start", getClass().getSimpleName());
        log.info("{}  => removeData", getClass().getSimpleName());
        removeData();
        log.info("{}  => addData", getClass().getSimpleName());
        addData();
        log.info("{}  => Success", getClass().getSimpleName());
    }

    protected abstract void addData();

    protected abstract void removeData();
}

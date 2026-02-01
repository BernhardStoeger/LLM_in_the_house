package scead.llminthehouse.base.common.utils;

import tools.jackson.databind.ObjectMapper;

public class ObjectMapperUtils
{
    public static ObjectMapper getObjectMapperWithLocalDates()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}

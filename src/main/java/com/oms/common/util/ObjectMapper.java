package com.oms.common.util;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Orika mapper exposed as a Spring Bean. It contains the configuration for the mapper factory and factory builder. It
 * will scan the Spring application context searching for mappers and converters to register them into the factory. To
 * use it we just need to autowire it into our class.
 * <p/>
 * @author asridhar2
 */
@Component("objectMapper")
public class ObjectMapper extends ConfigurableMapper implements ApplicationContextAware {

    private MapperFactory factory;
    private ApplicationContext applicationContext;

    public ObjectMapper() {
        super(false);
    }

    /**
     * Overrides the method from base class to add customer behavior
     *
     * @param factory Orika MapperFactory
     */
    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;
        addAllSpringBeans(applicationContext);
    }

    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        // Nothing to configure for now. base class method configure is used.

    }

    /**
     * Constructs and registers a {@link ClassMapBuilder} into the {@link MapperFactory} using a {@link Mapper}.
     *
     * @param mapper mapper
     */
    @SuppressWarnings("rawtypes")
    public void addMapper(Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType())
               .byDefault()
               .customize((Mapper) mapper)
               .register();
    }

    /**
     * Registers a {@link Converter} into the {@link ConverterFactory}.
     *
     * @param converter converter
     */
    public void addConverter(Converter<?, ?> converter) {
        factory.getConverterFactory().registerConverter(converter);
    }

    /**
     * Scans the appliaction context and registers all Mappers and Converters found in it.
     *
     * @param applicationContext spring context
     */
    @SuppressWarnings("rawtypes")
    private void addAllSpringBeans(final ApplicationContext applicationContext) {
        Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (Mapper mapper : mappers.values()) {
            addMapper(mapper);
        }
        Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        for (Converter converter : converters.values()) {
            addConverter(converter);
        }
    }

    /**
     * sets the spring context and initialiazes the orika
     *
     * @param applicationContext spring context
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }

}
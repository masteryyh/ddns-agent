package win.minaandyyh.ddnsagent.base.http.beans;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.http.annotations.api.EnableOpenApi;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 22454
 */
@Component
public class OpenApiBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(@NotNull AnnotationMetadata importingClassMetadata,
                                        @NotNull BeanDefinitionRegistry registry,
                                        @NotNull BeanNameGenerator importBeanNameGenerator) {
        boolean enable = importingClassMetadata
                .getAnnotations()
                .stream().anyMatch(x -> x.getType().equals(EnableOpenApi.class));
        if (!enable) {
            return;
        }
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableOpenApi.class.getName()));
        if (Objects.isNull(annotationAttributes)) {
            throw new RuntimeException("@EnableOpenApi was never config");
        }
        String[] baseScanPackages = annotationAttributes.getStringArray("baseScanPackages");
        // openApiScanner
        ClassPathScanningCandidateComponentProvider openApiClassScanner = new ClassPathScanningCandidateComponentProvider(false);
        // scan & remark
        List<Class<?>> interfaceTypes = new LinkedList<>();
        openApiClassScanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            Map<String, Object> openApiAnnotationAttributes = metadataReader
                    .getAnnotationMetadata()
                    .getAnnotationAttributes(OpenApi.class.getName());
            // doesn't mark with @OpenApi
            if (Objects.isNull(openApiAnnotationAttributes)) {
                return false;
            }

            // try find interface class
            try {
                Class<?> interfaceType = Class.forName(metadataReader.getClassMetadata().getClassName());
                interfaceTypes.add(interfaceType);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        });
        // find from basePackages
        for (String baseScanPackage : baseScanPackages) {
            openApiClassScanner.findCandidateComponents(baseScanPackage);
        }
        // registerBean
        for (Class<?> type : interfaceTypes) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                    .genericBeanDefinition(OpenApiFactoryBean.class);
            AbstractBeanDefinition definition = builder.getRawBeanDefinition();
            // set constructor arguments
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, type);
            definition.setConstructorArgumentValues(constructorArgumentValues);

            registry.registerBeanDefinition(type.getName(), definition);
        }

    }


    @Override
    public void registerBeanDefinitions(@NotNull AnnotationMetadata importingClassMetadata,
                                        @NotNull BeanDefinitionRegistry registry) {
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
    }

}

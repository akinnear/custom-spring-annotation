package com.github.kinnear.custombeanannotation.config;

import com.github.kinnear.custombeanannotation.annotation.Job;
import com.github.kinnear.custombeanannotation.annotation.JobRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;
import org.springframework.stereotype.Component;

@Component
public class JobAnnotationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(JobAnnotationBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isAbstract()) {
                continue;
            }
            Object source = beanDefinition.getSource();
            if (source == null) {
                continue;
            }
            if (!(source instanceof MethodMetadataReadingVisitor)) {
                continue;
            }
            MethodMetadataReadingVisitor factoryMethodMetadata = (MethodMetadataReadingVisitor) source;
            if (!factoryMethodMetadata.isAnnotated(Job.class.getCanonicalName())) {
                continue;
            }

            AnnotationAttributes annotationAttributes = factoryMethodMetadata.getAnnotationAttributes(Job.class.getCanonicalName());
            String runnerBeanName = annotationAttributes.getString("runnerBean");

            BeanDefinition runnerBeanDefinition = beanFactory.getBeanDefinition(runnerBeanName);
            Object runnerBeanSource = runnerBeanDefinition.getSource();
            if (runnerBeanSource == null) {
                continue;
            }
            if (!(runnerBeanSource instanceof MethodMetadataReadingVisitor)) {
                continue;
            }
            MethodMetadataReadingVisitor runnerBeanMetadata = (MethodMetadataReadingVisitor) runnerBeanSource;
            if (!runnerBeanMetadata.isAnnotated(JobRunner.class.getCanonicalName())) {
                continue;
            }

            com.github.kinnear.custombeanannotation.runner.JobRunner jobRunner = beanFactory.getBean(runnerBeanName, com.github.kinnear.custombeanannotation.runner.JobRunner.class);

            jobRunner.setup(() -> beanFactory.getBean(beanName, com.github.kinnear.custombeanannotation.job.Job.class));

            String newBeanName = beanName+".JobRunner";
            beanFactory.registerSingleton(newBeanName, jobRunner);

            logger.debug("Created bean: "+newBeanName);
        }
    }
}

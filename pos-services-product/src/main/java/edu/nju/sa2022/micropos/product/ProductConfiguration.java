package edu.nju.sa2022.micropos.product;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.sa2022.micropos.models.Product;
import edu.nju.sa2022.micropos.product.batch.JsonFileReader;
import edu.nju.sa2022.micropos.product.batch.ProductProcessor;
import edu.nju.sa2022.micropos.product.batch.ProductWriter;
import edu.nju.sa2022.micropos.services.ProductService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@EnableBatchProcessing
public class ProductConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public ItemReader<JsonNode> productJsonReader() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MultiResourceItemReaderBuilder<JsonNode> builder = new MultiResourceItemReaderBuilder<>();
        return builder.name("problemReader")
                .saveState(false)
                .resources(resolver.getResources("classpath:data/x**"))
                .delegate(new JsonFileReader())
                .build();
    }

    @Bean
    public ItemProcessor<JsonNode, Product> productProcessor() {
        return new ProductProcessor();
    }

    @Bean
    public ItemWriter<Product> productWriter(ProductService productService) {
        return new ProductWriter(productService);
    }

    @Bean
    public Step importProductsStep(StepBuilderFactory stepBuilderFactory,
                                   ItemReader<JsonNode> productJsonReader,
                                   ItemProcessor<JsonNode, Product> productProcessor,
                                   ItemWriter<Product> productWriter) {
        return stepBuilderFactory.get("importProducts")
                .<JsonNode, Product>chunk(20)
                .reader(productJsonReader)
                .processor(productProcessor)
                .writer(productWriter)
                .build();
    }

    @Bean
    public Job importProductsJob(JobBuilderFactory jobBuilderFactory, Step importProductsStep) {
        return jobBuilderFactory.get("importProducts")
                .flow(importProductsStep)
                .build()
                .build();
    }

}

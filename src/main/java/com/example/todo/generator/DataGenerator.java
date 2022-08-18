package com.example.todo.generator;


import com.example.todo.entity.Catelog;
import com.example.todo.entity.Tag;
import com.example.todo.repository.CatelogRepository;
import com.example.todo.repository.TagRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(CatelogRepository listRepo, TagRepository tagRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (listRepo.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Sample Person entities...");
            ExampleDataGenerator<Catelog> sampleCatelogRepositoryGenerator = new ExampleDataGenerator<>(
                    Catelog.class, LocalDateTime.of(2022, 8, 16, 0, 0, 0));
            sampleCatelogRepositoryGenerator.setData(Catelog::setName, DataType.DOMAIN);
            sampleCatelogRepositoryGenerator.setData(Catelog::setColor, DataType.WORD);
            sampleCatelogRepositoryGenerator.setData(Catelog::setIcon, DataType.WORD);
            listRepo.saveAll(sampleCatelogRepositoryGenerator.create(1, seed));

            ExampleDataGenerator<Tag> sampleTags = new ExampleDataGenerator<>(
                    Tag.class, LocalDateTime.of(2022, 8, 16, 0, 0, 0));
            sampleTags.setData(Tag::setName, DataType.DOMAIN);
            tagRepository.saveAll(sampleTags.create(2, seed));
            logger.info("Generated demo data");
        };
    }

}
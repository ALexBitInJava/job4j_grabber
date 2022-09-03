package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTemplateTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produce() {
        GeneratorTemplate generator = new GeneratorTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Petr Arsentev, Who are you?";
        String result = generator.produce(template, map);
        assertEquals(expected, result);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoTemplates() {
        GeneratorTemplate generator = new GeneratorTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subj", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void extraKeys() {
        GeneratorTemplate generator = new GeneratorTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject1", "you1");
        map.put("subject2", "you2");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        generator.produce(template, map);
    }
}
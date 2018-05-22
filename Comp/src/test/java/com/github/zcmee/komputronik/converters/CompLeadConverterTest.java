package com.github.zcmee.komputronik.converters;

import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CompLeadConverterTest {
    private final CompLeadConverter compLeadConverter = new CompLeadConverter();
    private Integer value1;
    private RecommendationStatus value2;

    public CompLeadConverterTest(Integer value1, RecommendationStatus value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1798542, RecommendationStatus.NEW},
                { null, null }
        });
    }

    @Test
    public void convertToDatabaseColumn() {
        Assert.assertEquals(value1, compLeadConverter.convertToDatabaseColumn(value2));
    }

    @Test
    public void convertToEntityAttribute() {
        Assert.assertEquals(compLeadConverter.convertToEntityAttribute(value1), value2);
    }

}
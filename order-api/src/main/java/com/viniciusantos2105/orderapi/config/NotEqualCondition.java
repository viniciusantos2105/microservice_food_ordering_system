package com.viniciusantos2105.orderapi.config;

import org.modelmapper.Condition;
import org.modelmapper.spi.MappingContext;

public class NotEqualCondition implements Condition<Object, Object> {
    @Override
    public boolean applies(MappingContext<Object, Object> context) {
        return !context.getSource().equals(context.getDestination());
    }
}
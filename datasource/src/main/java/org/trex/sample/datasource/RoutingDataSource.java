package org.trex.sample.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String type = ContextHolder.getDbType();
        System.out.println(type);
        return type;
    }
}

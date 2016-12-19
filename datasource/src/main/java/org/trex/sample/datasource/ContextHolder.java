package org.trex.sample.datasource;

import java.util.Random;

public class ContextHolder {


    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String DB_TYPE_W = "dataSourceW";
    public static String DB_TYPE_R = "dataSourceR";

    public static String getDbType() {
        String db = contextHolder.get();
        if (DB_TYPE_R.equals(db)) {
            Random r = new Random();
            int i = r.nextInt();
            if (i % 2 == 0) {
                return "dataSourceR1";
            } else {
                return "dataSourceR2";
            }
        } else {
            return DB_TYPE_W;
        }
    }

    public static void setDbType(String str) {
        contextHolder.set(str);
    }

    public static void clearDBType() {
        contextHolder.remove();
    }

}

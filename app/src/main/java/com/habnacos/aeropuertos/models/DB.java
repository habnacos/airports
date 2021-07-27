package com.habnacos.aeropuertos.models;

public class DB {
    public static final String nameDB = "Air";
    public static final String table_name = "airport";
    public static final String col_code = "code";
    public static final String col_name = "name";
    public static final String col_country = "country";
    public static final String col_city = "city";
    public static final String col_address = "address";
    public static final String col_latitude = "latitude";
    public static final String col_length = "length";

    public static final String create_table = "CREATE TABLE IF NOT EXISTS " + DB.table_name + " ( " +
            DB.col_code + " text primary key," +
            DB.col_name + " text," +
            DB.col_country + " text," +
            DB.col_city + " text," +
            DB.col_address + " text," +
            DB.col_latitude + " text," +
            DB.col_length + " text" +
            ");";
}

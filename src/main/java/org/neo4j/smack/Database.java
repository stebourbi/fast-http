package org.neo4j.smack;

import org.apache.log4j.Logger;
import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * @author mh
 * @since 14.11.11
 */
public class Database
{
    public static Logger log = Logger.getLogger(Database.class);

    public AbstractGraphDatabase graph;

    private final String databaseStoreDirectory;

    public Database( AbstractGraphDatabase db )
    {
        this.databaseStoreDirectory = db.getStoreDir();
        graph = db;
    }

    public AbstractGraphDatabase getGraphDB() {
        return graph;
    }

    public String getStoreDirectory() {
        return databaseStoreDirectory;
    }
}
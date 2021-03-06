/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.smack.api;

import org.neo4j.smack.api.rest.IndexService;
import org.neo4j.smack.api.rest.NodeService;
import org.neo4j.smack.api.rest.RelationshipService;
import org.neo4j.smack.api.rest.TraversalService;
import org.neo4j.smack.routing.RoutingDefinition;

public class DataAPI extends RoutingDefinition {

    private String baseUri;
    private String dataPath;

    public DataAPI(String host, int port) {
        this.baseUri = String.format("http://%s:%d",host,port);
        this.dataPath = "/db/data";
        addRoute("/tx",               new TransactionService());

        addRoute("",                  new CoreService());

        addRoute("/tx/{tx_id}",       new CoreService());
        // addRoute("/tx/{tx_id}/index", new IndexService());
        addRoute(dataPath, new NodeService(baseUri,dataPath));
        addRoute(dataPath, new RelationshipService(baseUri,dataPath));
        addRoute(dataPath, new IndexService(baseUri,dataPath));
        addRoute(dataPath, new TraversalService(baseUri,dataPath));
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getBaseUri() {
        return baseUri;
    }
}
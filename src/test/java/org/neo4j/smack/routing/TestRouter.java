package org.neo4j.smack.routing;

import org.junit.Test;
import org.neo4j.smack.event.Invocation;
import org.neo4j.smack.event.RequestEvent;
import org.neo4j.smack.event.Result;
import org.neo4j.smack.routing.Endpoint;
import org.neo4j.smack.routing.InvocationVerb;
import org.neo4j.smack.routing.ResourceNotFoundException;
import org.neo4j.smack.routing.Router;
import org.neo4j.smack.serialization.DeserializationStrategy;
import org.neo4j.smack.serialization.SerializationStrategy;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertNotNull;

public class TestRouter  {

    @Test
    public void shouldRouteVerbsCorrectly() {
       Endpoint e = new Endpoint() {

            @Override
            public void invoke(Invocation ctx, Result result) throws Exception { }

            @Override
            public InvocationVerb getVerb() {
                return InvocationVerb.GET;
            }

            @Override
            public DeserializationStrategy<?> getDeserializationStrategy() {
                return DeserializationStrategy.NO_OP;
            }

            public SerializationStrategy<?> getSerializationStrategy() {
                return SerializationStrategy.NO_OP;
            }

            public boolean hasAnnotation(
                    Class<? extends Annotation> annotationClass) {
                return false;
            }
        };
        
        Router r = new Router();
        r.addRoute("/db/data", e);
        r.compileRoutes();
        
        RequestEvent req = new RequestEvent();
        req.setVerb(InvocationVerb.GET);
        req.setPath("/db/data");
        
        Endpoint found = r.route(req);
        assertNotNull(found);
        
        ResourceNotFoundException ex = null;
        try {
            req = new RequestEvent();
            req.setVerb(InvocationVerb.POST);
            req.setPath("/db/data");
            
            r.route(req);
        } catch(ResourceNotFoundException rx) {
            ex = rx;
        }
        assertNotNull(ex);   
    }

    @Test
    public void shouldRouteSimplePathsCorrectly() {
       Endpoint e = new Endpoint() {

            @Override
            public void invoke(Invocation ctx,
                    Result response) throws Exception { }

            @Override
            public InvocationVerb getVerb() {
                return InvocationVerb.GET;
            }

            @Override
            public DeserializationStrategy<?> getDeserializationStrategy() {
                return DeserializationStrategy.NO_OP;
            }

            public SerializationStrategy<?> getSerializationStrategy() {
                return SerializationStrategy.NO_OP;
            }

            public boolean hasAnnotation(
                    Class<? extends Annotation> annotationClass) {
                return false;
            }
        };
        
        Router r = new Router();
        r.addRoute("/db/data", e);
        r.compileRoutes();
        
        RequestEvent req = new RequestEvent();
        req.setVerb(InvocationVerb.GET);
        req.setPath("/db/data");
        
        Endpoint found = r.route(req);
        assertNotNull(found);
        
        ResourceNotFoundException ex = null;
        try {
            req = new RequestEvent();
            req.setVerb(InvocationVerb.GET);
            req.setPath("/db/da");
            
            r.route(req);
        } catch(ResourceNotFoundException rx) {
            ex = rx;
        }
        assertNotNull(ex);   
    }
    
}

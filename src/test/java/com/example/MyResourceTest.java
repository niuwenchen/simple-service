package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    @Test
    public void testGetDevice(){
        final String testIp="10.11.58.164";
        final Device device = target.path("device").queryParam("ip",testIp)
                .request().get(Device.class);
        Assert.assertEquals(testIp,device.getIp());
    }

    @Test
    public void testUpdateDevice(){
        final String testIp= "10.11.58.163";
        final Device device = new Device(testIp);
        device.setStatus(1);
        // 这是什么意思
        Entity<Device> entity = Entity.entity(device, MediaType.APPLICATION_XML_TYPE);
        final Device result = target.path("device").request().put(entity,Device.class);
        Assert.assertEquals(1,result.getStatus());
    }

}


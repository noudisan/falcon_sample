/*
package com.zhiyi.common.test;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.Servlet;
import java.lang.annotation.*;
import java.util.*;

*/
/**
 * Created by hrs on 2014/11/19.
 *//*

public class HessianEmbeddedServer extends SpringJUnit4ClassRunner {

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<Server> servers = new ArrayList<>();

    public HessianEmbeddedServer(Class<?> clazz) throws InitializationError {
        super(clazz);
    }


    @Override
    public void run(RunNotifier notifier) {
        this.doStart(buildServer());
        try {
            super.run(notifier);
        } finally {
            for (Server server : servers) {
                server.getStopAtShutdown();
            }
        }
    }

    private void doStart(Map<EmbeddedServer, List<MockService>> maps) {
        for (EmbeddedServer embeddedServer : maps.keySet()) {
            try {
                startServer(embeddedServer, maps.get(embeddedServer));
            } catch (Exception e) {
                logger.error("Server start error!", e);
            }
        }
    }

    private Map<EmbeddedServer, List<MockService>> buildServer() {
        String[] contextPaths = findAnnotationValueByClass(Services.class).contextPath();
        int[] ports = findAnnotationValueByClass(Services.class).port();
        Class[] clz = findAnnotationValueByClass(Services.class).clz();
        String[] paths = findAnnotationValueByClass(Services.class).path();
        int length = contextPaths.length;
        Map<EmbeddedServer, List<MockService>> maps = new HashMap<>();
        for (int i = 0; i < length; i++) {
            EmbeddedServer server = new EmbeddedServer(contextPaths[i], ports[i]);
            if (null == maps.get(server)) {
                maps.put(server, new ArrayList<MockService>());
            }
            maps.get(server).add(new MockService(clz[i], paths[i]));
        }
        return maps;
    }


    private void startServer(EmbeddedServer embeddedServer, List<MockService> mockServices) {
        try {
            Server server = new Server();
            Connector connector = new SelectChannelConnector();
            connector.setPort(embeddedServer.getPort());
            server.setConnectors(new Connector[]{connector});
            ServletContextHandler root = new ServletContextHandler(null, "/" + embeddedServer.getContextPath(), ServletContextHandler.SESSIONS);
            server.setHandler(root);
            for (MockService service : mockServices) {
                Servlet servlet = (Servlet) Class.forName(service.getCla().getName()).newInstance();
                root.addServlet(new ServletHolder(servlet), "/" + service.path);
                server.start();
                servers.add(server);
            }
        } catch (Exception e) {
            throw new RuntimeException("Server start error!", e);
        }
    }


    private <T> T findAnnotationValueByClass(Class<T> annotationClass) {
        for (Annotation annotation : getTestClass().getAnnotations()) {
            if (annotation.annotationType().equals(annotationClass)) {
                return (T) annotation;
            }
        }
        throw new IllegalStateException(String.format("Can't find %s on test class: %s",
                annotationClass, getTestClass()));
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface Services {
        public String[] contextPath();

        public int[] port();

        public Class[] clz();

        public String[] path();
    }

    private static class EmbeddedServer {

        private int port;

        private String contextPath;

        public EmbeddedServer() {
        }

        public EmbeddedServer(String contextPath, int port) {
            this.port = port;
            this.contextPath = contextPath;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EmbeddedServer that = (EmbeddedServer) o;
            if (port != that.port) return false;
            if (contextPath != null ? !contextPath.equals(that.contextPath) : that.contextPath != null) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = port;
            result = 31 * result + (contextPath != null ? contextPath.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "EmbeddedServer{" +
                    "contextPath='" + contextPath + '\'' +
                    ", port=" + port +
                    '}';
        }
    }

    public static class MockService {

        private Class cla;

        private String path;

        public MockService() {
        }

        public MockService(Class cla, String path) {
            this.cla = cla;
            this.path = path;
        }

        public Class getCla() {
            return cla;
        }

        public void setCla(Class cla) {
            this.cla = cla;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

}
*/

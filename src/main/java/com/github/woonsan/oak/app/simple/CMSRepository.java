package com.github.woonsan.oak.app.simple;

import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.stereotype.Component;

import javax.jcr.*;
import java.util.UUID;

@Component
public class CMSRepository {

    private Repository repository;

    public CMSRepository() {
        try {
            repository = JcrUtils.getRepository();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public String publishText(String text) {
        String nodeName = UUID.randomUUID().toString();

        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

            Node root = session.getRootNode();

            // Store content
            Node txtNode = root.addNode(nodeName);
            txtNode.setProperty("content", text);
            session.save();

            return nodeName;

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.logout();
        }
    }

    public String getText(String nodeName) {
        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
            Node root = session.getRootNode();

            // Retrieve content
            Node node = root.getNode(nodeName);

            if (node == null) {
                return null;
            }
            return node.getProperty("content").getString();

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.logout();
        }
    }

}

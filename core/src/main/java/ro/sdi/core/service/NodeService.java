package ro.sdi.core.service;

import java.util.List;

import ro.sdi.core.model.Node;

public interface NodeService
{
    void add(String name, Integer totalCapacity);

    List<Node> getNodes();

    Node update(String name, Integer totalCapacity);

    List<Node> getAvailableNodes();

    Integer trySchedulePod(Long nodeId, Long podId);
}

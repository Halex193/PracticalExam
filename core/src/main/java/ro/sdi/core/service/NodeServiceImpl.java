package ro.sdi.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ro.sdi.core.exceptions.ElementNotFoundException;
import ro.sdi.core.model.Node;
import ro.sdi.core.model.Pod;
import ro.sdi.core.repository.NodeRepository;
import ro.sdi.core.repository.PodRepository;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService
{
    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    PodRepository podRepository;

    @Override
    public void add(String name, Integer totalCapacity)
    {
        Node node = new Node(name, totalCapacity);
        log.trace("Adding node {}", node);

        /*nodeRepository.findById(id).ifPresent(
                (item) ->
                {
                    throw new AlreadyExistingElementException(String.format(
                            "Node %d already exists",
                            id
                    ));
                });*/
        nodeRepository.save(node);
    }

    @Override
    public List<Node> getNodes()
    {
        log.trace("Retrieving all nodes");
        return nodeRepository.findAll();
    }

    @Override
    @Transactional
    public Node update(String name, Integer totalCapacity)
    {
        Node node = new Node(name, totalCapacity);
        log.trace("Updating node {}", node);
        Example<Node> example = Example.of(new Node(name, null));
        Node oldNode = nodeRepository.findAll().stream().filter(n -> n.getName().equals(name)).findAny().get();
        oldNode.setTotalCapacity(totalCapacity);
        nodeRepository.save(oldNode);
        return oldNode;
    }

    @Override
    public List<Node> getAvailableNodes()
    {
        return nodeRepository.findAll()
                             .stream()
                             .filter(node -> node.getTotalCapacity() > node.getCurrentCost())
                             .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer trySchedulePod(Long nodeId, Long podId)
    {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ElementNotFoundException("Node"));
        Pod pod = podRepository.findById(podId).orElseThrow(() -> new ElementNotFoundException("Pod"));

        int currentCapacity = node.getTotalCapacity() - node.getCurrentCost();
        if (pod.getCost() < currentCapacity)
        {
            pod.setNode(node);
            return currentCapacity - pod.getCost();
        }
        return null;
    }
}

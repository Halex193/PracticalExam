package ro.sdi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ro.sdi.core.exceptions.AlreadyExistingElementException;
import ro.sdi.core.exceptions.ElementNotFoundException;
import ro.sdi.core.model.Node;
import ro.sdi.core.service.NodeService;
import ro.sdi.web.converter.NodeConverter;
import ro.sdi.web.dto.NodeDto;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Slf4j
@RestController
public class NodeController
{
    private final NodeService nodeService;

    private final NodeConverter nodeConverter;

    public NodeController(
            NodeService nodeService,
            NodeConverter nodeConverter
    )
    {
        this.nodeService = nodeService;
        this.nodeConverter = nodeConverter;
    }


    @RequestMapping(value = "/nodes", method = GET)
    public List<NodeDto> getNodes()
    {
        List<Node> nodes = nodeService.getNodes();
        log.trace("Get nodes: {}", nodes);
        return nodeConverter.toDtos(nodes);
    }

    @RequestMapping(value = "/nodes", method = POST)
    public ResponseEntity<?> addNode(@RequestBody NodeDto nodeDto)
    {
        Node node = nodeConverter.toModel(nodeDto);
        if(!node.getName().matches(".{5,}") || node.getTotalCapacity() <= 0)
        {
            log.trace("Node data invalid: {}", node);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        try
        {
            nodeService.add(node.getName(), node.getTotalCapacity());
        }
        catch (AlreadyExistingElementException e)
        {
            log.trace("Node {} already exists", node);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        log.trace("Node {} added", node);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/nodes", method = PUT)
    public ResponseEntity<?> updateNode(@RequestBody NodeDto nodeDto)
    {
        Node node = nodeConverter.toModel(nodeDto);
        try
        {
            nodeService.update(node.getName(), node.getTotalCapacity());
        }
        catch (ElementNotFoundException e)
        {
            log.trace("Node could not be updated");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.trace("Node with was updated: {}", node);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@RequestMapping(value = "/nodes/filter/{name}", method = GET)
    public List<NodeDto> filterNodesByName(@PathVariable String name)
    {
        return nodeConverter.toDtos(nodeService.filterNodesByName(name));
    }*/
}

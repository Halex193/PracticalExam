package ro.sdi.web.converter;

import org.springframework.stereotype.Component;

import ro.sdi.core.model.Node;
import ro.sdi.web.dto.NodeDto;

@Component
public class NodeConverter implements Converter<Node, NodeDto>
{

    @Override
    public Node toModel(NodeDto node)
    {
        return new Node(node.getName(), node.getTotalCapacity());
    }

    @Override
    public NodeDto toDto(Node node)
    {
        return new NodeDto(node.getName(), node.getTotalCapacity());
    }
}

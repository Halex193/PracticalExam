package ro.sdi.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "podLinks",
                attributeNodes = @NamedAttributeNode(value = "podLinks")
        ),


        @NamedEntityGraph(
                name = "podLinksWithNodes",
                attributeNodes = @NamedAttributeNode(
                        value = "podLinks",
                        subgraph = "linkPod"
                ),
                subgraphs = @NamedSubgraph(
                        name = "linkPod",
                        attributeNodes = @NamedAttributeNode(value = "pod")
                )
        )
})*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pod extends BaseEntity<Long> implements Serializable
{
    @Column(unique = true)
    private String name;

    private int cost;

    public Pod(String name, Integer cost)
    {
        super(0L);
        this.name = name;
        this.cost = cost;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Node node;
}

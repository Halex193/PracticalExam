package ro.sdi.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "nodeLinks",
                attributeNodes = @NamedAttributeNode(value = "nodeLinks")
        ),


        @NamedEntityGraph(
                name = "nodeLinksWithPods",
                attributeNodes = @NamedAttributeNode(
                        value = "nodeLinks",
                        subgraph = "linkPod"
                ),
                subgraphs = @NamedSubgraph(
                        name = "linkPod",
                        attributeNodes = @NamedAttributeNode(value = "pod")
                )
        )
})*/
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Node extends BaseEntity<Long> implements Serializable
{
    @Column(unique = true)
    private String name;

    private Integer totalCapacity;

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Pod> pods = new HashSet<>();

    public Node(String name, Integer totalCapacity)
    {
        super(0L);
        this.name = name;
        this.totalCapacity = totalCapacity;
    }

    public Integer getCurrentCost()
    {
        return getPods()
                .stream()
                .mapToInt(Pod::getCost)
                .sum();
    }

    /*public Set<Link> getPods()
    {
        return nodeLinks.stream().collect(Collectors.toUnmodifiableSet());
    }

    public Set<Pod> getPods()
    {
        return nodeLinks.stream()
                           .map(Link::getPod)
                           .collect(Collectors.toUnmodifiableSet());
    }

    public void deletePod(Pod pod)
    {
        nodeLinks.removeIf(link -> link.getPod().id.equals(pod.id));
    }

    public void linkPod(Pod pod, LocalDateTime time)
    {
        nodeLinks.add(new Link(this, pod, time));
    }

    public void updateLinkTime(Pod pod, LocalDateTime dateTime)
    {
        nodeLinks.stream()
                    .filter(link -> link.getPod().id.equals(pod.id))
                    .forEach(link -> link.setTime(dateTime));
    }*/
}

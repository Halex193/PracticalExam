package ro.sdi.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public abstract class BaseEntity<ID extends Serializable> implements Serializable
{

    @NotNull(message = "Invalid id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
}

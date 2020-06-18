package ro.sdi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import ro.sdi.core.model.BaseEntity;

@NoRepositoryBean
@Transactional
public interface CloudAppRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>
{

}

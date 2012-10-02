  
package org.springframework.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author fouad
 * 
 */
public abstract class JpaAbstractDao implements Serializable {

    protected EntityManager entityManager;

    @PersistenceContext(name="sparklPU", unitName="sparklPU")
    public void setEntityManager(EntityManager entityManager) {
            this.entityManager = entityManager;
    }


}

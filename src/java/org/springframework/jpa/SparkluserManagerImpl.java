
package org.springframework.jpa;

import java.io.Serializable;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.model.entities.SparklUserDao;
import org.springframework.model.entities.Sparkluser;
import org.springframework.model.entities.Userrole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


 /**
 * @author fouad
 */
@Repository("userman")
@Transactional(readOnly = true)
public class SparkluserManagerImpl extends JpaAbstractDao implements SparklUserDao, 
                                                    Serializable  {

    public SparkluserManagerImpl() {
    }

    /**
     * Das Passwort wird geändert.
     */
    @Override
    public String changePasswd(String newPasswd, Sparkluser user) {
            try {
                    user.setPasswd(newPasswd);
                    user = entityManager.merge(user);
                    entityManager.flush();
                    return "myhome";
            } catch (Exception ex) {                    
                    return "error_database";
            } finally {
                    entityManager.clear();
            }

    }

    @Override
    public String createUser(String username, String passwd){
        Sparkluser sparkluser = new Sparkluser();
        Userrole urole = new Userrole();

        sparkluser.setUsername(username);
        sparkluser.setPasswd(passwd);

        try {
            entityManager.persist(sparkluser);
            entityManager.flush();
        } catch (Exception ex) {                    
            return "error_database";
        }
        urole.setUserid(sparkluser);
//                urole.setUserrole(Role.USER_ROLE.roleName()); 
        try {
            entityManager.persist(urole);
            entityManager.flush();
            return "welcome";
        } catch (Exception ex) {                    
            return "error_database";
        } finally {
            entityManager.clear();
        }
        
    }

    @Override
    public Sparkluser getUserById(int id) {
        try {
            Query q = entityManager
                        .createQuery("SELECT object(u) FROM Sparkluser AS u WHERE "
                                    + "u.userid = :id");

            q.setParameter("id", id);
            return (Sparkluser) q.getSingleResult();
        } finally {
            entityManager.clear();
        }
    }

    @Override
    public Sparkluser getUserByUsername(String username) {
        try {
            Query q = entityManager.createNamedQuery("Sparkluser.findByUsername");
            q.setParameter("username", username);
            return (Sparkluser) q.getSingleResult();

        } catch (NoResultException nre) {
                return null;
        } finally {
            entityManager.clear();
        }
        
    }


}
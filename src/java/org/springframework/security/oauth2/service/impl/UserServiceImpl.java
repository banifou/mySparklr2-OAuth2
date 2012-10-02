package org.springframework.security.oauth2.service.impl;

import org.springframework.security.oauth2.service.UserService;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.model.entities.SparklUserDao;
import org.springframework.model.entities.Sparkluser;
import org.springframework.model.entities.Userrole;

/**
 * @author fouad
 * 
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

    private SparklUserDao sparklUserDao;

    /**
     * @see org.springframework.security.userdetails.UserDetailsService#
     *      loadUserByUsername(java.lang.String)
     */
    
    public UserDetails loadUserByUsername(String username)
                    throws UsernameNotFoundException, DataAccessException {
        try {
            // Benutzer laden
            Sparkluser sparkluser = this.sparklUserDao.getUserByUsername(username);

            // Rollen des Benutzers laden
            Collection<Userrole> userroles = sparkluser.getUserroleCollection();
            List<String> theRoles = new ArrayList<String>();
                    
            // Rollen für spring-security umwandeln
            for (Userrole userrole : userroles) {
                    theRoles.add(userrole.getUserrole());
            }
            Collection<GrantedAuthority> ga = AuthorityUtils.
                          createAuthorityList(theRoles.toArray(new String[]{}));

            // TODO Userstatus abfragen und die folgenden boolean Flags
            // entsprechend setzen
            return new User(sparkluser.getUsername(), sparkluser.getPasswd(),
                                        true, true, true, true, ga);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Account not found", e);
        }
    }
    
    /**
     * the sparklUserDao to set
     *
     * @param sparklUserDao
     */
    public void setSparklUserDao(SparklUserDao sparklUserDao) {
            this.sparklUserDao = sparklUserDao;
    }

}

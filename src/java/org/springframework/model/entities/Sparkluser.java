
package org.springframework.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fouad
 */
@Entity
@SequenceGenerator(name = "SPARKLUSER_SEQUENCE", sequenceName = "sparkluser_userid_seq", allocationSize = 1)
@Table(name = "sparkluser", catalog = "sparkl", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Sparkluser.findAll", query = "SELECT s FROM Sparkluser s"),
    @NamedQuery(name = "Sparkluser.findByUserid", query = "SELECT s FROM Sparkluser s WHERE s.userid = :userid"),
    @NamedQuery(name = "Sparkluser.findByUsername", query = "SELECT s FROM Sparkluser s WHERE s.username = :username"),
    @NamedQuery(name = "Sparkluser.findByPasswd", query = "SELECT s FROM Sparkluser s WHERE s.passwd = :passwd")})
public class Sparkluser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPARKLUSER_SEQUENCE")
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "passwd")
    private String passwd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Userrole> userroleCollection;

    public Sparkluser() {
    }

    public Sparkluser(Integer userid) {
        this.userid = userid;
    }

    public Sparkluser(Integer userid, String username, String passwd) {
        this.userid = userid;
        this.username = username;
        this.passwd = passwd;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Collection<Userrole> getUserroleCollection() {
        return userroleCollection;
    }

    public void setUserroleCollection(Collection<Userrole> userroleCollection) {
        this.userroleCollection = userroleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sparkluser)) {
            return false;
        }
        Sparkluser other = (Sparkluser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sparkl.db.Sparkluser[ userid=" + userid + " ]";
    }
    
}

















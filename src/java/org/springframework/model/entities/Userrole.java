
package org.springframework.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fouad
 */
@Entity
@SequenceGenerator(name="USERROLE_SEQUENCE",sequenceName="userrole_roleid_seq",allocationSize=1)
@Table(name = "userrole", catalog = "sparkl", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u"),
    @NamedQuery(name = "Userrole.findByRoleid", query = "SELECT u FROM Userrole u WHERE u.roleid = :roleid"),
    @NamedQuery(name = "Userrole.findByUserrole", query = "SELECT u FROM Userrole u WHERE u.userrole = :userrole")})
public class Userrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USERROLE_SEQUENCE")
    @Basic(optional = false)
    @Column(name = "roleid")
    private Integer roleid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "userrole")
    private String userrole;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Sparkluser userid;

    public Userrole() {
    }

    public Userrole(Integer roleid) {
        this.roleid = roleid;
    }

    public Userrole(Integer roleid, String userrole) {
        this.roleid = roleid;
        this.userrole = userrole;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public Sparkluser getUserid() {
        return userid;
    }

    public void setUserid(Sparkluser userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleid != null ? roleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sparkl.db.Userrole[ roleid=" + roleid + " ]";
    }
    
}

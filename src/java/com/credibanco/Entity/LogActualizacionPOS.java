/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credibanco.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sami
 */
@Entity
@Table(name = "logActualizacionPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogActualizacionPOS.findAll", query = "SELECT l FROM LogActualizacionPOS l"),
    @NamedQuery(name = "LogActualizacionPOS.findById", query = "SELECT l FROM LogActualizacionPOS l WHERE l.id = :id"),
    @NamedQuery(name = "LogActualizacionPOS.findByLogDescription", query = "SELECT l FROM LogActualizacionPOS l WHERE l.logDescription = :logDescription"),
    @NamedQuery(name = "LogActualizacionPOS.findByLogDate", query = "SELECT l FROM LogActualizacionPOS l WHERE l.logDate = :logDate"),
    @NamedQuery(name = "LogActualizacionPOS.findByLogSource", query = "SELECT l FROM LogActualizacionPOS l WHERE l.logSource = :logSource")})
public class LogActualizacionPOS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "LogDescription")
    private String logDescription;
    @Column(name = "LogDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;
    @Size(max = 2147483647)
    @Column(name = "LogSource")
    private String logSource;

    public LogActualizacionPOS() {
    }

    public LogActualizacionPOS(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogActualizacionPOS)) {
            return false;
        }
        LogActualizacionPOS other = (LogActualizacionPOS) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.LogActualizacionPOS[ id=" + id + " ]";
    }
    
}

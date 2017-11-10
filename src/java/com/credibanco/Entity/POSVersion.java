package com.credibanco.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Daniel
 * @version 1.0
 */
@Entity
@Table(name = "POSVersion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "POSVersion.findAll", query = "SELECT p FROM POSVersion p"),
    @NamedQuery(name = "POSVersion.findById", query = "SELECT p FROM POSVersion p WHERE p.id = :id"),
    @NamedQuery(name = "POSVersion.findByField1", query = "SELECT p FROM POSVersion p WHERE p.field1 = :field1"),
    @NamedQuery(name = "POSVersion.findByField2", query = "SELECT p FROM POSVersion p WHERE p.field2 = :field2"),
    @NamedQuery(name = "POSVersion.findByNumVersion", query = "SELECT p FROM POSVersion p WHERE p.numVersion = :numVersion"),
    @NamedQuery(name = "POSVersion.findByLinkVideo", query = "SELECT p FROM POSVersion p WHERE p.linkVideo = :linkVideo")})
public class POSVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Field1")
    private String field1;

    @Column(name = "Field2")
    private String field2;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Num_Version")
    private String numVersion;

    @Column(name = "Link_Video")
    private String linkVideo;

    @Lob
    @Column(name = "File_Download")
    private byte[] fileDownload;

    public POSVersion() {
    }

    public POSVersion(Integer id) {
        this.id = id;
    }

    public POSVersion(Integer id, String numVersion) {
        this.id = id;
        this.numVersion = numVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(String numVersion) {
        this.numVersion = numVersion;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public byte[] getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(byte[] fileDownload) {
        this.fileDownload = fileDownload;
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
        if (!(object instanceof POSVersion)) {
            return false;
        }
        POSVersion other = (POSVersion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.POSVersion[ id=" + id + " ]";
    }
}
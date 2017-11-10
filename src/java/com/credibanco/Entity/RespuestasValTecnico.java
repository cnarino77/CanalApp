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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "respuestas_val_tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestasValTecnico.findAll", query = "SELECT r FROM RespuestasValTecnico r"),
    @NamedQuery(name = "RespuestasValTecnico.findByIdRespuesta", query = "SELECT r FROM RespuestasValTecnico r WHERE r.idRespuesta = :idRespuesta"),
    @NamedQuery(name = "RespuestasValTecnico.findByTextoRespuesta", query = "SELECT r FROM RespuestasValTecnico r WHERE r.textoRespuesta = :textoRespuesta"),
    @NamedQuery(name = "RespuestasValTecnico.findByFechaRespuesta", query = "SELECT r FROM RespuestasValTecnico r WHERE r.fechaRespuesta = :fechaRespuesta")})
public class RespuestasValTecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Respuesta")
    private Integer idRespuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Texto_Respuesta")
    private String textoRespuesta;
    @Column(name = "Fecha_Respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @JoinColumn(name = "Id_Pregunta", referencedColumnName = "Id_Pregunta")
    @ManyToOne(optional = false)
    private PreguntasValTecnico idPregunta;
    @JoinColumn(name = "Resp_Id_Tecnico", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private TecnicoVisita respIdTecnico;

    public RespuestasValTecnico() {
    }

    public RespuestasValTecnico(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public RespuestasValTecnico(Integer idRespuesta, String textoRespuesta) {
        this.idRespuesta = idRespuesta;
        this.textoRespuesta = textoRespuesta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public PreguntasValTecnico getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(PreguntasValTecnico idPregunta) {
        this.idPregunta = idPregunta;
    }

    public TecnicoVisita getRespIdTecnico() {
        return respIdTecnico;
    }

    public void setRespIdTecnico(TecnicoVisita respIdTecnico) {
        this.respIdTecnico = respIdTecnico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestasValTecnico)) {
            return false;
        }
        RespuestasValTecnico other = (RespuestasValTecnico) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.RespuestasValTecnico[ idRespuesta=" + idRespuesta + " ]";
    }
    
}

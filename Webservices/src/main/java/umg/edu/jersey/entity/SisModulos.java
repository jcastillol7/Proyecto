/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmavin
 */
@Entity
@Table(name = "sis_modulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisModulos.findAll", query = "SELECT s FROM SisModulos s"),
    @NamedQuery(name = "SisModulos.findById", query = "SELECT s FROM SisModulos s WHERE s.id = :id"),
    @NamedQuery(name = "SisModulos.findByNombre", query = "SELECT s FROM SisModulos s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SisModulos.findByFechasys", query = "SELECT s FROM SisModulos s WHERE s.fechasys = :fechasys")})
public class SisModulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechasys")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasys;

    public SisModulos() {
    }

    public SisModulos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechasys() {
        return fechasys;
    }

    public void setFechasys(Date fechasys) {
        this.fechasys = fechasys;
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
        if (!(object instanceof SisModulos)) {
            return false;
        }
        SisModulos other = (SisModulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "umg.edu.jersey.entity.SisModulos[ id=" + id + " ]";
    }
    
}

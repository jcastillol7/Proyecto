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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmavin
 */
@Entity
@Table(name = "sis_accesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisAccesos.findAll", query = "SELECT s FROM SisAccesos s"),
    @NamedQuery(name = "SisAccesos.findById", query = "SELECT s FROM SisAccesos s WHERE s.id = :id"),
    @NamedQuery(name = "SisAccesos.findByIdUsuario", query = "SELECT s FROM SisAccesos s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SisAccesos.findByIdModulo", query = "SELECT s FROM SisAccesos s WHERE s.idModulo = :idModulo"),
    @NamedQuery(name = "SisAccesos.findByIdPrograma", query = "SELECT s FROM SisAccesos s WHERE s.idPrograma = :idPrograma"),
    @NamedQuery(name = "SisAccesos.findByAcceso", query = "SELECT s FROM SisAccesos s WHERE s.acceso = :acceso"),
    @NamedQuery(name = "SisAccesos.findByFechasys", query = "SELECT s FROM SisAccesos s WHERE s.fechasys = :fechasys")})
public class SisAccesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_modulo")
    private Integer idModulo;
    @Column(name = "id_programa")
    private Integer idPrograma;
    @Column(name = "acceso")
    private Character acceso;
    @Column(name = "fechasys")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasys;

    public SisAccesos() {
    }

    public SisAccesos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Character getAcceso() {
        return acceso;
    }

    public void setAcceso(Character acceso) {
        this.acceso = acceso;
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
        if (!(object instanceof SisAccesos)) {
            return false;
        }
        SisAccesos other = (SisAccesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "umg.edu.jersey.entity.SisAccesos[ id=" + id + " ]";
    }
    
}

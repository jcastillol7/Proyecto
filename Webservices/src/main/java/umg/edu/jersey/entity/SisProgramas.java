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
@Table(name = "sis_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisProgramas.findAll", query = "SELECT s FROM SisProgramas s"),
    @NamedQuery(name = "SisProgramas.findById", query = "SELECT s FROM SisProgramas s WHERE s.id = :id"),
    @NamedQuery(name = "SisProgramas.findByIdModulo", query = "SELECT s FROM SisProgramas s WHERE s.idModulo = :idModulo"),
    @NamedQuery(name = "SisProgramas.findByNombre", query = "SELECT s FROM SisProgramas s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SisProgramas.findByTitulo", query = "SELECT s FROM SisProgramas s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "SisProgramas.findByTipo", query = "SELECT s FROM SisProgramas s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SisProgramas.findByFechasys", query = "SELECT s FROM SisProgramas s WHERE s.fechasys = :fechasys")})
public class SisProgramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_modulo")
    private Integer idModulo;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "tipo")
    private Character tipo;
    @Column(name = "fechasys")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasys;

    public SisProgramas() {
    }

    public SisProgramas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof SisProgramas)) {
            return false;
        }
        SisProgramas other = (SisProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "umg.edu.jersey.entity.SisProgramas[ id=" + id + " ]";
    }
    
}

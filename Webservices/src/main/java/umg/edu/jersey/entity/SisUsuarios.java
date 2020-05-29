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
@Table(name = "sis_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisUsuarios.findAll", query = "SELECT s FROM SisUsuarios s"),
    @NamedQuery(name = "SisUsuarios.findById", query = "SELECT s FROM SisUsuarios s WHERE s.id = :id"),
    @NamedQuery(name = "SisUsuarios.findByUsuario", query = "SELECT s FROM SisUsuarios s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "SisUsuarios.findByNombre", query = "SELECT s FROM SisUsuarios s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SisUsuarios.findByApellido", query = "SELECT s FROM SisUsuarios s WHERE s.apellido = :apellido"),
    @NamedQuery(name = "SisUsuarios.findByClave", query = "SELECT s FROM SisUsuarios s WHERE s.clave = :clave"),
    @NamedQuery(name = "SisUsuarios.findByCorreo", query = "SELECT s FROM SisUsuarios s WHERE s.correo = :correo"),
    @NamedQuery(name = "SisUsuarios.findByHabilitado", query = "SELECT s FROM SisUsuarios s WHERE s.habilitado = :habilitado"),
    @NamedQuery(name = "SisUsuarios.findByColumna8", query = "SELECT s FROM SisUsuarios s WHERE s.columna8 = :columna8")})
public class SisUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 80)
    @Column(name = "correo")
    private String correo;
    @Column(name = "habilitado")
    private Character habilitado;
    @Column(name = "Columna 8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date columna8;

    public SisUsuarios() {
    }

    public SisUsuarios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Character getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Character habilitado) {
        this.habilitado = habilitado;
    }

    public Date getColumna8() {
        return columna8;
    }

    public void setColumna8(Date columna8) {
        this.columna8 = columna8;
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
        if (!(object instanceof SisUsuarios)) {
            return false;
        }
        SisUsuarios other = (SisUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "umg.edu.jersey.entity.SisUsuarios[ id=" + id + " ]";
    }
    
}

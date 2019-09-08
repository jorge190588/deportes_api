package com.deportes.deportes_api.tablas;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="deporte")

public class Deporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @Pattern(regexp = ".*([a-zA-Z0-9]{2}$)",message="El nombre debe contener letras y numeros con una longitud mínima de 2 caracteres")
    private String nombre;
    
    @Pattern(regexp = "([a-zA-Z0-9]{10})",message="El código debe tener 10 caracteres")
    @Column(name="codigo")
    private String codigo;
    
    private Date createdAt;
    private Date updatedAt;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date date) {
		this.updatedAt = date;
	}
   
}

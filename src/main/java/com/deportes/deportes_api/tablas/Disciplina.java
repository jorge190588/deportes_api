package com.deportes.deportes_api.tablas;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="disciplina")
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @Pattern(regexp = ".*([a-zA-Z0-9]{2}$)",message="El nombre debe contener letras y numeros con una longitud mínima de 2 caracteres")
    private String nombre;
    
    @Pattern(regexp = "([a-zA-Z0-9]{10})",message="El código debe tener 10 caracteres")
    @Column(name="codigo", unique=true)
    private String codigo;
    
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne
    @JoinColumn(name="deporte_id", insertable=false, updatable=false)
    private Deporte deporte; 
        
    @Column(name = "deporte_id")
    @JsonProperty("deporte_id")
    private Integer deporte_id;
    
    
    
    public Deporte getDeporte() {
		return deporte;
	}
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	public Integer getDeporte_id() {
		return deporte_id;
	}
	public void setDeporte_id(Integer deporte_id) {
		this.deporte_id = deporte_id;
	}
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

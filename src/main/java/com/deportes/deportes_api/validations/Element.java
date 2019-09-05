package com.deportes.deportes_api.validations;

import java.util.Date;

public class Element {
	private int id;
	private ElementType elementType;
	private Entiti entiti;
	private String idelement;
	private String label;
	private Boolean isRequired;
	private Boolean isUnique;
	private String pattern;
	private String patternMessage;
	private int orderElement;
	private Boolean isCreate;
	private Boolean isUpdate;
	private Boolean isDelete;
	private Date createdAt;
	private Date updatedAt;
	private String mask;
	private String maskProperty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ElementType getElementType() {
		return elementType;
	}
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}
	public Entiti getEntiti() {
		return entiti;
	}
	public void setEntiti(Entiti entiti) {
		this.entiti = entiti;
	}
	public String getIdelement() {
		return idelement;
	}
	public void setIdelement(String idelement) {
		this.idelement = idelement;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Boolean getIsUnique() {
		return isUnique;
	}
	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getPatternMessage() {
		return patternMessage;
	}
	public void setPatternMessage(String patternMessage) {
		this.patternMessage = patternMessage;
	}
	public int getOrderElement() {
		return orderElement;
	}
	public void setOrderElement(int orderElement) {
		this.orderElement = orderElement;
	}
	public Boolean getIsCreate() {
		return isCreate;
	}
	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}
	public Boolean getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getMaskProperty() {
		return maskProperty;
	}
	public void setMaskProperty(String maskProperty) {
		this.maskProperty = maskProperty;
	}
}

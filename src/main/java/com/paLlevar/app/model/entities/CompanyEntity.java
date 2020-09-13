package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="company")
public class CompanyEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre", length=50)
	private String nombre;
	@Column(name="ruc", length=11)
	private String ruc;
	
	@Column(name="business_name")
	private String businessName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name = "responsible_payment_name", length = 450)
	private String responsiblePaymentName;

	@Column(name = "responsible_payment_phone", length = 45)
	private String responsiblePaymentPhone;

	@Column(name = "responsible_payment_email", length = 450)
	private String responsiblePaymentEmail;
	
	@Column(name = "aniversary_date", columnDefinition="DATE")
	private LocalDate anniversaryDate;
	
	@Column(name="create_date", columnDefinition="TIMESTAMP")
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_admin_id", referencedColumnName = "id")
	private UserEntity userAdmin;
	
	@JsonIgnore
	@Column(name = "photo", updatable = false)
	private byte[] photo;
	
	@JsonIgnore
	@Column(name = "image_panel", updatable = false)
	private byte[] imagePanel;
	
	@Column(name = "business_line_code")
	private String businessLineCode;

	@Column(name = "payment_method")
	private String  paymentMethodCode;

	@Column(name="status",length=20)
	private String status;
	
	@Column(name="estimated_time")
	private Integer estimatedTime;
	
	@Column(name="qualification")
	private Double qualification;
	
	@Column(name="attention_schedule")
	private String attentionSchedule;
	
	
	@Column(name="send_protocol")
	private String sendProtocol;
	
	@Column(name="hour_attention_protocol")
	private String hourAttentionProtocol;
	
	@Column(name="time_estimated_protocol")
	private String timeEstimatedProtocol;
	
	@Column(name="additional_information_protocol")
	private String additionalInformationProtocol;
	
	@ManyToOne
	@JoinColumn(name = "place_id", referencedColumnName = "id")
	private PlaceEntity place;
	
	public String getBusinessLineCode() {
		return businessLineCode;
	}
	public void setBusinessLineCode(String businessLineCode) {
		this.businessLineCode = businessLineCode;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getResponsiblePaymentName() {
		return responsiblePaymentName;
	}
	public void setResponsiblePaymentName(String responsiblePaymentName) {
		this.responsiblePaymentName = responsiblePaymentName;
	}
	public String getResponsiblePaymentPhone() {
		return responsiblePaymentPhone;
	}
	public void setResponsiblePaymentPhone(String responsiblePaymentPhone) {
		this.responsiblePaymentPhone = responsiblePaymentPhone;
	}
	public String getResponsiblePaymentEmail() {
		return responsiblePaymentEmail;
	}
	public void setResponsiblePaymentEmail(String responsiblePaymentEmail) {
		this.responsiblePaymentEmail = responsiblePaymentEmail;
	}
	public LocalDate getAnniversaryDate() {
		return anniversaryDate;
	}
	public void setAnniversaryDate(LocalDate anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getAttentionSchedule() {
		return attentionSchedule;
	}
	public void setAttentionSchedule(String attentionSchedule) {
		this.attentionSchedule = attentionSchedule;
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
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	public UserEntity getUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(UserEntity userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public Double getQualification() {
		return qualification;
	}
	public void setQualification(Double qualification) {
		this.qualification = qualification;
	}
	public byte[] getImagePanel() {
		return imagePanel;
	}
	public void setImagePanel(byte[] imagePanel) {
		this.imagePanel = imagePanel;
	}
	public String getSendProtocol() {
		return sendProtocol;
	}
	public void setSendProtocol(String sendProtocol) {
		this.sendProtocol = sendProtocol;
	}
	public String getHourAttentionProtocol() {
		return hourAttentionProtocol;
	}
	public void setHourAttentionProtocol(String hourAttentionProtocol) {
		this.hourAttentionProtocol = hourAttentionProtocol;
	}
	public String getTimeEstimatedProtocol() {
		return timeEstimatedProtocol;
	}
	public void setTimeEstimatedProtocol(String timeEstimatedProtocol) {
		this.timeEstimatedProtocol = timeEstimatedProtocol;
	}
	public String getAdditionalInformationProtocol() {
		return additionalInformationProtocol;
	}
	public void setAdditionalInformationProtocol(String additionalInformationProtocol) {
		this.additionalInformationProtocol = additionalInformationProtocol;
	}
	public PlaceEntity getPlace() {
		return place;
	}
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
	
	
}

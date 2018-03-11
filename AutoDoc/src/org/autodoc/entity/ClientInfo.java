package org.autodoc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "client_information")
@SequenceGenerator(name = "sequence", sequenceName = "client_information_client_id_seq", allocationSize=0)
public class ClientInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name = "client_id")
	private int clientId;							//primary_key
	
	@Column(name = "file_no")
	private int fileNo;								//integer
	
	@Column(name = "client_name")
	private String clientName;						//150 - Not Null
	
	@Column(name = "group_names")
	private String groupNames;						//1500 - Not Null
	
	@Column(name = "contact_name")
	private String contactName;						//100
	
	@Column(name = "partner_name")
	private String partnerName;						//200

	@Column(name = "incorporation_date")
	private Date incorporationDate;					//DATE - Not Null
	
	@Column(name = "year_end")
	private int yearEnd; 							//integer
	
	@Column(name="address_line1")
	private String addressLine1;					//255 - Not Null
	
	@Column(name="address_line2")
	private String addressLine2;					//255
	
	@Column(name="city")
	private String city;							//100 - Not Null
	
	@Column(name="country_code")
	private int countryCode;						//Not Null
	
	@Column(name="post_code")
	private String postCode;						//10
	
	@Column(name = "telephone_no")
	private String telephoneNo;						//20
	
	@Column(name = "email_id")
	private String emailId;							//100
	
	@Column(name = "created_date", updatable = false)
	private Date createdDate;						//TIMESTAMP - Not Null
	
	@Column(name = "created_by", updatable = false)
	private String createdBy;						//100 - Not Null
	
	@Column(name = "updated_date")
	private Date updatedDate;						//TIMESTAMP
	
	@Column(name = "updated_by")
	private String updatedBy;						//100
	
	@Column(name = "status")
	private int status;								//smallInt - Not Null

	public int getClientId()
	{
		return clientId;
	}

	public void setClientId(int clientId)
	{
		this.clientId = clientId;
	}

	public int getFileNo()
	{
		return fileNo;
	}

	public void setFileNo(int fileNo)
	{
		this.fileNo = fileNo;
	}

	public String getClientName()
	{
		return clientName;
	}

	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public String getGroupNames()
	{
		return groupNames;
	}

	public void setGroupNames(String groupNames)
	{
		this.groupNames = groupNames;
	}

	public String getContactName()
	{
		return contactName;
	}

	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	
	public String getPartnerName()
	{
		return partnerName;
	}

	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}

	public Date getIncorporationDate()
	{
		return incorporationDate;
	}

	public void setIncorporationDate(Date incorporationDate)
	{
		this.incorporationDate = incorporationDate;
	}

	public int getYearEnd()
	{
		return yearEnd;
	}

	public void setYearEnd(int yearEnd)
	{
		this.yearEnd = yearEnd;
	}

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public int getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(int countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	public String getTelephoneNo()
	{
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo)
	{
		this.telephoneNo = telephoneNo;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
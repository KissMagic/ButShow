package com.butshow.entity;

import java.util.Date;

/**
 * UserDefault entity. @author MyEclipse Persistence Tools
 */

public class UserDefault implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String uuid;
	private String username;
	private String loginpassword;
	private String phone;
	private String email;
	private Integer sex;
	private Integer isrealemail;
	private String personalname;
	private String personalid;
	private String address;
	private String usertype;
	private String signupsource;
	private String signupip;
	private Date signuptime;
	private Date updatatime;
	private String remark;

	// Constructors

	/** default constructor */
	public UserDefault() {
	}

	/** minimal constructor */
	public UserDefault(String loginpassword, Integer isrealemail,
			String signupsource, Date signuptime) {
		this.loginpassword = loginpassword;
		this.isrealemail = isrealemail;
		this.signupsource = signupsource;
		this.signuptime = signuptime;
	}

	/** full constructor */
	public UserDefault(String uuid, String username, String loginpassword,
			String phone, String email, Integer sex, Integer isrealemail,
			String contactname, String contactphone, String address,
			String personalname, String personalid, String usertype,
			String signupsource, String signupip, Date signuptime,
			Date updatatime, String remark) {
		this.uuid = uuid;
		this.username = username;
		this.loginpassword = loginpassword;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.isrealemail = isrealemail;
		this.personalname = personalname;
		this.personalid = personalid;
		this.address = address;
		this.usertype = usertype;
		this.signupsource = signupsource;
		this.signupip = signupip;
		this.signuptime = signuptime;
		this.updatatime = updatatime;
		this.remark = remark;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getIsrealemail() {
		return this.isrealemail;
	}

	public void setIsrealemail(Integer isrealemail) {
		this.isrealemail = isrealemail;
	}

	public String getPersonalname() {
		return personalname;
	}

	public void setPersonalname(String personalname) {
		this.personalname = personalname;
	}

	public String getPersonalid() {
		return personalid;
	}

	public void setPersonalid(String personalid) {
		this.personalid = personalid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getSignupsource() {
		return this.signupsource;
	}

	public void setSignupsource(String signupsource) {
		this.signupsource = signupsource;
	}

	public String getSignupip() {
		return this.signupip;
	}

	public void setSignupip(String signupip) {
		this.signupip = signupip;
	}

	public Date getSignuptime() {
		return this.signuptime;
	}

	public void setSignuptime(Date signuptime) {
		this.signuptime = signuptime;
	}

	public Date getUpdatatime() {
		return this.updatatime;
	}

	public void setUpdatatime(Date updatatime) {
		this.updatatime = updatatime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "UserDefault [id=" + id + ", uuid=" + uuid + ", username="
				+ username + ", loginpassword=" + loginpassword + ", phone="
				+ phone + ", email=" + email + ", sex=" + sex
				+ ", isrealemail=" + isrealemail + ", personalname="
				+ personalname + ", personalid=" + personalid + ", address="
				+ address + ", usertype=" + usertype + ", signupsource="
				+ signupsource + ", signupip=" + signupip + ", signuptime="
				+ signuptime + ", updatatime=" + updatatime + ", remark="
				+ remark + "]";
	}

}
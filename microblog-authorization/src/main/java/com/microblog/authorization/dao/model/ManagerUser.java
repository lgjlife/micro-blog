package com.microblog.authorization.dao.model;

import lombok.ToString;

import java.util.Date;

@ToString
public class ManagerUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.email_active
     *
     * @mbggenerated
     */
    private Byte emailActive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.header_url
     *
     * @mbggenerated
     */
    private String headerUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.login_time
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.last_login_time
     *
     * @mbggenerated
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.gender
     *
     * @mbggenerated
     */
    private String gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.age
     *
     * @mbggenerated
     */
    private Byte age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.id
     *
     * @return the value of manager_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.id
     *
     * @param id the value for manager_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.username
     *
     * @return the value of manager_user.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.username
     *
     * @param username the value for manager_user.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.password
     *
     * @return the value of manager_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.password
     *
     * @param password the value for manager_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.salt
     *
     * @return the value of manager_user.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.salt
     *
     * @param salt the value for manager_user.salt
     *
     * @mbggenerated
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.phone
     *
     * @return the value of manager_user.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.phone
     *
     * @param phone the value for manager_user.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.email
     *
     * @return the value of manager_user.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.email
     *
     * @param email the value for manager_user.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.email_active
     *
     * @return the value of manager_user.email_active
     *
     * @mbggenerated
     */
    public Byte getEmailActive() {
        return emailActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.email_active
     *
     * @param emailActive the value for manager_user.email_active
     *
     * @mbggenerated
     */
    public void setEmailActive(Byte emailActive) {
        this.emailActive = emailActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.header_url
     *
     * @return the value of manager_user.header_url
     *
     * @mbggenerated
     */
    public String getHeaderUrl() {
        return headerUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.header_url
     *
     * @param headerUrl the value for manager_user.header_url
     *
     * @mbggenerated
     */
    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.create_time
     *
     * @return the value of manager_user.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.create_time
     *
     * @param createTime the value for manager_user.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.login_time
     *
     * @return the value of manager_user.login_time
     *
     * @mbggenerated
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.login_time
     *
     * @param loginTime the value for manager_user.login_time
     *
     * @mbggenerated
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.last_login_time
     *
     * @return the value of manager_user.last_login_time
     *
     * @mbggenerated
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.last_login_time
     *
     * @param lastLoginTime the value for manager_user.last_login_time
     *
     * @mbggenerated
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.gender
     *
     * @return the value of manager_user.gender
     *
     * @mbggenerated
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.gender
     *
     * @param gender the value for manager_user.gender
     *
     * @mbggenerated
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.age
     *
     * @return the value of manager_user.age
     *
     * @mbggenerated
     */
    public Byte getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.age
     *
     * @param age the value for manager_user.age
     *
     * @mbggenerated
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.status
     *
     * @return the value of manager_user.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.status
     *
     * @param status the value for manager_user.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}
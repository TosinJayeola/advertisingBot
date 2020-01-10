package org.enigmatic.elonbot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long id;

    @NotNull
    @Column(name = "chat_id")
    private Long chatId;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "balance")
    private int balance;

    @NotNull
    @Column(name = "free_balance")
    private int freeBalance;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    private List<AdModel> adModels = new ArrayList<>();

    public ContactModel() {
    }

    public ContactModel(@NotNull Long chatId, @NotNull Integer userId, String userName, String phoneNumber, @NotNull int balance) {
        this.chatId = chatId;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.freeBalance = 5;
    }
    public ContactModel(@NotNull Long chatId, @NotNull Integer userId, String userName, String phoneNumber, @NotNull int balance, List<AdModel> adModels) {
        this.chatId = chatId;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.freeBalance = 5;
        this.adModels = adModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getFreeBalance() {
        return freeBalance;
    }

    public void setFreeBalance(int freeBalance) {
        this.freeBalance = freeBalance;
    }

    public List<AdModel> getAdModels() {
        return adModels;
    }

    public void setAdModels(List<AdModel> adModels) {
        this.adModels = adModels;
    }
}
package org.enigmatic.elonbot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ads")
public class AdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ad_id")
    private long addId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactModel contact;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionModel region;

    @Column(name = "photo_id")
    private String photoId;
    @Column
    private String text;
    @Column
    private String price;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    public AdModel() {
    }

    public AdModel(@NotNull CategoryModel category, @NotNull RegionModel region, String photoId, String text, String price, @NotNull String phoneNumber) {
        this.category = category;
        this.region = region;
        this.photoId = photoId;
        this.text = text;
        this.price = price;
        this.phoneNumber = phoneNumber;
    }

    public long getAddId() {
        return addId;
    }

    public void setAddId(long addId) {
        this.addId = addId;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public RegionModel getRegion() {
        return region;
    }

    public void setRegion(RegionModel region) {
        this.region = region;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
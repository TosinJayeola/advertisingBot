package org.enigmatic.elonbot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private long categoryId;
    @Column
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<AdModel> adModels = new ArrayList<>();

    public CategoryModel() {
    }

    public CategoryModel(String name) {
        this.name = name;
    }

    public CategoryModel(String name, List<AdModel> adModels) {
        this.name = name;
        this.adModels = adModels;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdModel> getAdModels() {
        return adModels;
    }

    public void setAdModels(List<AdModel> adModels) {
        this.adModels = adModels;
    }
}
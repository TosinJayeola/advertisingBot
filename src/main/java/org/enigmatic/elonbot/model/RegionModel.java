package org.enigmatic.elonbot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="regions")
public class RegionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private Long regionId;

    @Column
    private String name;

    @Column(name = "region_kind")
    private String regionKind;

    @ManyToOne
    @JoinColumn(name = "parent_region_id")
    private RegionModel parentRegion;

    @OneToMany(mappedBy = "parentRegion", fetch = FetchType.EAGER)
    private List<RegionModel> childRegions = new ArrayList<>();

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<AdModel> adModels = new ArrayList<>();

    public RegionModel() {
    }

    public RegionModel(Integer regionId){

    }

    public RegionModel(String name, String regionKind) {
        this.name = name;
        this.regionKind = regionKind;
    }

    public RegionModel(String name, String regionKind, RegionModel parentRegion) {
        this.name = name;
        this.regionKind = regionKind;
        this.parentRegion = parentRegion;
    }

    public RegionModel(String name) {
        this.name = name;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionKind() {
        return regionKind;
    }

    public void setRegionKind(String regionKind) {
        this.regionKind = regionKind;
    }

    public RegionModel getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(RegionModel parentRegion) {
        this.parentRegion = parentRegion;
    }

    public List<RegionModel> getChildRegions() {
        return childRegions;
    }

    public void setChildRegions(List<RegionModel> childRegions) {
        this.childRegions = childRegions;
    }

    public List<AdModel> getAdModels() {
        return adModels;
    }

    public void setAdModels(List<AdModel> adModels) {
        this.adModels = adModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionModel that = (RegionModel) o;
        return Objects.equals(regionId, that.regionId) ||
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, name);
    }
}

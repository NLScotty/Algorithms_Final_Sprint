package com.keyin.Models;

import jakarta.persistence.*;

@Entity
public class StoredTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "LONGTEXT")
    String tree;

    String inputOrder;

    public StoredTree(){

    }

    public StoredTree(long id, String tree, String inputOrder){
        this.id = id;
        this.tree = tree;
        this.inputOrder = inputOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getInputOrder() {
        return inputOrder;
    }

    public void setInputOrder(String inputOrder) {
        this.inputOrder = inputOrder;
    }
}

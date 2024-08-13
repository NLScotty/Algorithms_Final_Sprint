package com.keyin.Models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BSTree {
    private BSNode root;

    public BSTree(){

    }

    public BSTree(BSNode root){
        this.root = root;
    }

    public BSNode getRoot() {
        return root;
    }

    public void setRoot(BSNode root){
        this.root = root;
    }

    public void insert(int value){
        if(this.root == null){
            this.root = new BSNode(value);
        } else {
            this.root.insertValue(value);
        }
    }

    @Override
    public String toString() {
        return "BSTree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        System.out.println(tree.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(tree);
            System.out.println(jsonString);
            BSTree reAssembledTree = objectMapper.readValue(jsonString,BSTree.class);
            System.out.println(reAssembledTree);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

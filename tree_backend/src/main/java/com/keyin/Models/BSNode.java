package com.keyin.Models;

public class BSNode {
    private int value;
    private BSNode left;
    private BSNode right;

    public BSNode(){

    }

    public BSNode(int value){
        this.value = value;
    }

    public BSNode(int value, BSNode left, BSNode right){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BSNode getLeft() {
        return left;
    }

    public void setLeft(BSNode left) {
        this.left = left;
    }

    public BSNode getRight() {
        return right;
    }

    public void setRight(BSNode right) {
        this.right = right;
    }

    public void insertValue(int value){
        if(this.value == 0){
            this.value = value;
        } else if (this.value > value) {
            if(this.left == null){
                this.left = new BSNode(value);
            } else {
                this.left.insertValue(value);
            }
        } else if (this.value < value){
            if (this.right == null){
                this.right = new BSNode(value);
            } else {
                this.right.insertValue(value);
            }
        }
        //else, this.value is equal to value, and we ignore it!
    }

    @Override
    public String toString() {
        return "BSNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

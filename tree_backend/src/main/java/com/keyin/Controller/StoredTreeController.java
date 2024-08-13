package com.keyin.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.Models.BSTree;
import com.keyin.Models.CTRequest;
import com.keyin.Models.StoredTree;
import com.keyin.Service.StoredTreeService;
import com.keyin.util.BestOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StoredTreeController {
    @Autowired
    private StoredTreeService storedTreeService;

    @GetMapping("/tree/all")
    public List<BSTree> getAllTrees(){
        List<StoredTree> storedTrees = storedTreeService.getAllStoredTrees();
        ArrayList<BSTree> trees = new ArrayList<>();
        for (StoredTree storedTree : storedTrees){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                BSTree tree = objectMapper.readValue(storedTree.getTree(),BSTree.class);
                trees.add(tree);
            } catch (Exception e){
                continue;
            }
        }
        return trees;
    }

    @GetMapping("/tree/{id}")
    public BSTree getTree(@PathVariable Long id){
        StoredTree storedTree = storedTreeService.getStoredTree(id);
        if(storedTree != null){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                BSTree tree = objectMapper.readValue(storedTree.getTree(),BSTree.class);
                return tree;
            } catch (Exception e){
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/tree/detailed/all")
    public List<StoredTree> getAllStoredTrees(){
        return storedTreeService.getAllStoredTrees();
    }

    @GetMapping("/tree/detailed/{id}")
    public StoredTree getStoredTree(@PathVariable Long id){
        return storedTreeService.getStoredTree(id);
    }

    @PostMapping("/tree/create")
    public BSTree createTree(@RequestBody CTRequest request){
        BSTree newTree = new BSTree();
        if(request.isPerfect()){
            List<Integer> bestOrder = BestOrder.getBestOrder(Arrays.stream(request.getNumbers()).boxed().collect(Collectors.toList()));
            for (int nummber : bestOrder){
                newTree.insert(nummber);
            }
        } else {
            for(int number : request.getNumbers()){
                newTree.insert(number);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(newTree);
        } catch (Exception e){
            return null;
        }
        if(request.isPerfect()){
            List<Integer> bestOrder = BestOrder.getBestOrder(Arrays.stream(request.getNumbers()).boxed().collect(Collectors.toList()));
            StoredTree newStoredTree = new StoredTree(0, jsonString, bestOrder.toString());
            storedTreeService.createStoredTree(newStoredTree);
            return newTree;
        } else {
            List<Integer> insertOrder = Arrays.stream(request.getNumbers()).boxed().collect(Collectors.toList());
            StoredTree newStoredTree = new StoredTree(0, jsonString, insertOrder.toString());
            storedTreeService.createStoredTree(newStoredTree);
            return newTree;
        }
    }

}

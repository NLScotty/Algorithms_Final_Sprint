package com.keyin.Service;

import com.keyin.Models.StoredTree;
import com.keyin.Repository.StoredTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoredTreeService {
    @Autowired
    private StoredTreeRepository storedTreeRepository;

    public List<StoredTree> getAllStoredTrees(){
        return (List<StoredTree>) storedTreeRepository.findAll();
    }

    public StoredTree getStoredTree(Long id){
        Optional<StoredTree> result = storedTreeRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public StoredTree createStoredTree(StoredTree tree){
        return storedTreeRepository.save(tree);
    }
}

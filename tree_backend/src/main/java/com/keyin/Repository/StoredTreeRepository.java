package com.keyin.Repository;

import com.keyin.Models.StoredTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredTreeRepository extends CrudRepository<StoredTree, Long> {

}

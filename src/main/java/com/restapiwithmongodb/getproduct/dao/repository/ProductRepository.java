package com.restapiwithmongodb.getproduct.dao.repository;

import com.restapiwithmongodb.getproduct.models.entities.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
}

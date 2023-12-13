/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject.dao;

import com.mycompany.ecommerceproject.entities.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Abhishek Shah
 */
public class ProductDao {
     private final SessionFactory factory;
    public ProductDao(SessionFactory factory)
    {
        this.factory=factory;
    }
    public boolean saveProduct(Product product)
    {
        boolean f = false;
        try{
      Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.close();
        f = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            f = false;
        }
        return f;
    }
    //get all products
    
    public List<Product> getAllProducts(){
         Session s = this.factory.openSession();
         Query query = s.createQuery("from Product");
         List<Product> list = query.list();
         return list;
    }
     public List<Product> getAllProductsById(int cid){
         Session s = this.factory.openSession();
         Query query = s.createQuery("from Product as p where p.category.categoryId=:id");
         query.setParameter("id", cid);  
         List<Product> list = query.list();
         return list;
    }
}

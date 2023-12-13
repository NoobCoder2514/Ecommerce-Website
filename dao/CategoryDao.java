 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerceproject.dao;

import com.mycompany.ecommerceproject.entities.Category;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Abhishek Shah
 */
public class CategoryDao {
    private final SessionFactory factory;
    public CategoryDao(SessionFactory factory)
    {
        this.factory=factory;
    }
    public int saveCategory(Category cat)
    {
        int catId;
        try (Session session = this.factory.openSession()) {
            Transaction tx = session.beginTransaction();
            catId = (int) session.save(cat);
            tx.commit();
        }
        return catId;
    }

    /**
     *
     * @return
     */
    public List<Category> getCategories()
    {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Category");
        List<Category> list = query.list();
        return list;
    }
    
    public Category getCategoryById(int cid)
    {
        Category cat = null;
        try{
           Session session = this.factory.openSession();
           cat = session.get(Category.class, cid);
           session.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cat;
    }
}

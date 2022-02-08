package com.example.oneviziontest.DAO;

import com.example.oneviziontest.Configuration.HibernateConfig;
import com.example.oneviziontest.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final HibernateConfig hibernateConfig;

    public BookDAO(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public List<Book> getAllBook(){
        return this.hibernateConfig.getMysqlSession().createQuery("from Book").list();
    }

    public void saveBook(Book book){
        Session session = this.hibernateConfig.getMysqlSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    public List<Book> getAllBookOrderByAuthor(){
        return this.hibernateConfig.getMysqlSession().createQuery("from Book order by author").list();
    }

}

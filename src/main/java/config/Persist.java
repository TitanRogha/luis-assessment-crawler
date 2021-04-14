package config;

import models.WebSite;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Persist {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WebCrawler");

    public static void addWebSite(int id,String type,String queryInput,String target,String url2){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = entityManager.getTransaction();
            et.begin();

            WebSite webSite = new WebSite();
            WebSite.SearchAction searchAction1= new WebSite.SearchAction();
            WebSite.Url url = new WebSite.Url();

            searchAction1.setType(type);
            searchAction1.setQuery_input(queryInput);
            searchAction1.setTarget(target);
            url.setUrl(url2);

            webSite.setPotentialAction(searchAction1);
            webSite.setUrl(url);

            entityManager.persist(webSite);
            et.commit();
        }catch (Exception e){
            if(et !=null){
                et.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    public static void addProduct(int id,String type,String queryInput,String target,String url2){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = entityManager.getTransaction();
            et.begin();

            WebSite webSite = new WebSite();
            WebSite.SearchAction searchAction1= new WebSite.SearchAction();
            WebSite.Url url = new WebSite.Url();

            searchAction1.setType(type);
            searchAction1.setQuery_input(queryInput);
            searchAction1.setTarget(target);
            url.setUrl(url2);

            webSite.setPotentialAction(searchAction1);
            webSite.setUrl(url);

            entityManager.persist(webSite);
            et.commit();
        }catch (Exception e){
            if(et !=null){
                et.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }


}

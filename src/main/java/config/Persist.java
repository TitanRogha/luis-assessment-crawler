package config;

import models.ImageObject;
import models.Product;
import models.WebSite;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Persist {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WebCrawler");

    /**
     *
     * @param searchAction
     * @param url
     */
    public static void addWebSite(WebSite.SearchAction searchAction, WebSite.Url url) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();

            WebSite webSite = new WebSite();
            webSite.setPotentialAction(searchAction);
            webSite.setUrl(url);

            entityManager.persist(webSite);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param productId
     * @param audience
     * @param brand
     * @param description
     * @param imageUrl
     * @param qtin13
     * @param name
     * @param offer
     * @param sku
     * @param url
     * @param weight
     */
    public static void addProduct(int productId,Product.Audience audience, String brand, String description, Product.ImageUrl imageUrl, long qtin13, String name, Product.Offers offer, int sku, Product.Url url, Product.Weight weight) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction et ;
        try {
            et = entityManager.getTransaction();
            et.begin();
            Product product = new Product(productId,audience,brand,description,qtin13,imageUrl,name,offer,sku,url,weight);
            Product product1= entityManager.merge(product);
            entityManager.persist(product1);
            et.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @param author
     * @param contentUrl
     * @param description
     * @param mainEntityOfPage
     * @param name
     * @param representativeOfPage
     */
    public static void addImageObject(int id,String author, ImageObject.ContentUrl contentUrl, String description, ImageObject.MainEntityOfPage mainEntityOfPage,String name,Boolean representativeOfPage) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction et;
        try {
            et = entityManager.getTransaction();
            et.begin();
            ImageObject imageObject = new ImageObject(id,author,contentUrl,description,mainEntityOfPage,name,representativeOfPage);
            imageObject.setId(id);
            ImageObject imageObject1= entityManager.merge(imageObject);
            entityManager.persist(imageObject1);
            et.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

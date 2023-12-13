package net.codejava;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateParentChildTest {

	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		SessionFactory factory = new MetadataSources(registry)
				.buildMetadata()
				.buildSessionFactory();
		
		Session session = factory.openSession();
		
//		createCategories(session);
		listCategories(session);
		
		session.close();
		factory.close();
	}

	private static void createCategories(Session session) {
		Category electronics = new Category("Electronics");
		Category mobilePhones = new Category("Mobile phones", electronics);
		Category washingMachines = new Category("Washing machines", electronics);
		
		electronics.addChild(mobilePhones);
		electronics.addChild(washingMachines);
		
		Category iPhone = new Category("iPhone", mobilePhones);
		Category samsung = new Category("Samsung", mobilePhones);
		
		mobilePhones.addChild(iPhone);
		mobilePhones.addChild(samsung);
		
		Category galaxy = new Category("Galaxy", samsung);
		samsung.addChild(galaxy);
		
		session.save(electronics);
	}
	
	private static void listCategories(Session session) {
		Category electronics = session.get(Category.class, 1);

		Set<Category> children = electronics.getChildren();
		
		System.out.println(electronics.getName());
		
		for (Category child : children) {
			System.out.println("--" + child.getName());
			printChildren(child, 1);
		}		
	}
	
	private static void printChildren(Category parent, int subLevel) {
		Set<Category> children = parent.getChildren();
		
		for (Category child : children) {
			for (int i = 0; i <= subLevel; i++) System.out.print("--");
					
			System.out.println(child.getName());
			
			printChildren(child, subLevel + 1);
		}
	}
}

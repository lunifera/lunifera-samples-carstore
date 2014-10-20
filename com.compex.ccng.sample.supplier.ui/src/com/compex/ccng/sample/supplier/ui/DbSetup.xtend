package com.compex.ccng.sample.supplier.ui

import com.compex.ccng.sample.supplier.model.Supplier
import com.google.gwt.i18n.server.testing.Gender
import java.util.Date
import java.util.Random
import javax.persistence.EntityManagerFactory
import com.compex.ccng.sample.supplier.model.ABC
import com.compex.ccng.sample.supplier.model.Address
import com.compex.ccng.sample.supplier.model.Contact

class DbSetup {

	def setupDB(EntityManagerFactory emf) {
		val em = emf.createEntityManager
		em.transaction.begin
		
		val firstname = #['Florian', 'John', 'Klemens', 'Jennifer', 'Sabrina']
		val lastname = #['Pirchner', 'Johnnson', 'Miller', 'Mayer', 'Edler']
		val mails = #['john@example.com', 'florian@example.com', 'klemens@example.com', 'bar@example.com',
			'foo@example.com']
		val prio = #[ABC.A, ABC.B, ABC.C]
		val locked = #[true, false]
		val streetAddress = #['On the beach 7', 'At the montain 9', 'Behind the lake 17', 'In the middle 78',
			'Near the sun 12']
		val city = #['Munich', 'San Francisco', 'Vienna', 'Moon',
			'Jupiter']
		val housenumber = #['1a', '12334', '99 c', '75/2',
			'9997564']
		val texts = #['Hey you', 'Hey there', 'Whats up', 'Are you fine?', 'Come on...']
		val company = #['Lunifera', 'Compex Commerce', 'Bakery', 'Coffe house', 'Millers fishery']
		val zip = #["112345", "99786", "4e557", "76844", "7775423"]
		val images = #["ad.gif", "at.gif", "au.gif", "ba.gif", "bg.gif", "bn.gif", "bs.gif", "bv.gif", "ca.gif",
			"cd.gif", "ch.gif", "ci.gif", "ck.gif", "cn.gif", "de.gif"]

		val Random rnd = new Random(5)
		for (i : 0 .. 500) {
			val d = new Supplier
			d.gln = rnd.nextLong().toString
			d.imageURL = "icons/flags/" + images.get(rnd.nextInt(5))
			d.lastOrder = new Date()
			d.number_of_orders = rnd.nextInt(250)
			d.yearly_turnover = rnd.nextInt(200000)
			d.priority = prio.get(rnd.nextInt(3))
			d.locked = locked.get(rnd.nextInt(2))
			
			val Address address = new Address
			address.streetname = streetAddress.get(rnd.nextInt(5))
			address.city = city.get(rnd.nextInt(5))
			address.housenumber = housenumber.get(rnd.nextInt(5))
			address.zipcode = zip.get(rnd.nextInt(5))
			d.headquarter = address
			
			for(x : 0..rnd.nextInt(7)){
				val c = new Contact
				c.birthday =  new Date()
				c.firstname = firstname.get(rnd.nextInt(5))
				c.lastname = lastname.get(rnd.nextInt(5))
				c.imageURL = "icons/flags/" + images.get(rnd.nextInt(5))
				
				val Address homeAddress = new Address
				homeAddress.streetname = streetAddress.get(rnd.nextInt(5))
				homeAddress.city = city.get(rnd.nextInt(5))
				homeAddress.housenumber = housenumber.get(rnd.nextInt(5))
				c.homeAddress = homeAddress
				
				d.addToContacts(c)
			}
			
			em.persist(d)
		}
		
		em.transaction.commit
	}
	
}

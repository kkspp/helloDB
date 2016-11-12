package kr.ac.hansung.spring.csemall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		/* spring 컨테이너 만들기 */
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/ac/hansung/spring/csemall/beans/beans.xml");
		
		/*getBean을 통해 OfferDAO에대한 ref를 가져온 다음  row값 출력 */
		OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");	//offerDAO에대한 
		System.out.println("The record count is "+offerDAO.getRowCount());
		
		List<Offer> offerList = offerDAO.getOffers();
		
		for(Offer offer: offerList) {
			System.out.println(offer);
		}
		
		Offer offer = new Offer("nykim", "nykim@hansung.ac.kr", "an instructor of spring framework");
		if(offerDAO.insert(offer)) {
			System.out.println("Object is inserted successfully");
		}
		else {
			System.out.println("Object insertion failed");
		}
		
		offer = offerDAO.getOffer("nykim");
		System.out.println(offer);
		
		/*값 update 됐는지 확인*/
		offer.setName("Namyun Kim");
		if(offerDAO.update(offer))
			System.out.println("update with object: "+offer);
		else
			System.out.println("Cannot update an object");
		
		/*값 삭제 됐는지 확인*/
		if(offerDAO.delete(offer.getId()))
			System.out.println("object is deleted");
		else
			System.out.println("cannot delete object");
		
		context.close();
	}
}

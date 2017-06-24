package com.thirtySix.core;
//package com.thirtySix.Core;
//
//import java.io.Serializable;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.thirtySix.model.Customer;
//import com.thirtySix.model.FurnishClass;
//import com.thirtySix.model.Item;
//import com.thirtySix.model.ItemClass;
//import com.thirtySix.model.SeatMap;
//import com.thirtySix.model.Furnish;
//import com.thirtySix.repository.CustomerDAO;
//import com.thirtySix.repository.FurnishClassDAO;
//import com.thirtySix.repository.ItemClassDAO;
//import com.thirtySix.repository.ItemDAO;
//import com.thirtySix.repository.SeatMapDAO;
//import com.thirtySix.repository.SeatPositionDAO;
//
//@Component
//@Transactional
//public class DBManager {
//
//	@Autowired
//	private CustomerDAO customerDAO = null;
//
//	@Autowired
//	private ItemClassDAO itemClassDAO = null;
//
//	@Autowired
//	private ItemDAO itemDAO = null;
//
////	@Autowired
////	private BookingDAO bookingDAO = null;
//
//	@Autowired
//	private SeatMapDAO seatMapDAO = null;
//
//	@Autowired
//	private SeatPositionDAO seatPositionDAO = null;
//
//	@Autowired
//	private FurnishClassDAO furnishClassDAO = null;
//
//	/**
//	 * 取得該ID的class entity
//	 * 
//	 * @param classID
//	 * @return
//	 */
//	public FurnishClass getFurnishClass(String classID) {
//		return furnishClassDAO.get(classID);
//	}
//
//	/**
//	 * 新增座位表
//	 * 
//	 * @param po
//	 */
//	public void insertSeatMap(SeatMap po) {
//		seatMapDAO.insert(po);
//	}
//
//	/**
//	 * 更新座位表
//	 * 
//	 * @param po
//	 */
//	public void updateSeatMap(SeatMap po) {
//		seatMapDAO.update(po);
//	}
//
//	/**
//	 * 取得座位表
//	 * 
//	 * @return
//	 */
//	public List<SeatMap> getSeatMap() {
//		return seatMapDAO.getAll();
//	}
//
//	/**
//	 * 取得用餐中顧客
//	 * 
//	 * @return
//	 */
//	public List<Customer> getDiningCustomer() {
//		return customerDAO.getDiningCustomer();
//	}
//
//	/**
//	 * 新增用餐中顧客
//	 */
//	public Serializable insertCustomer(Customer po) {
//		return customerDAO.insert(po);
//	}
//
//	/**
//	 * 更新顧客資訊(check-out)
//	 * 
//	 * @param po
//	 */
//	public void updateCustomer(Customer po) {
//		customerDAO.update(po);
//	}
//
//	/**
//	 * 取得所有項目種類
//	 * 
//	 * @return
//	 */
//	public List<ItemClass> getAllItemClass() {
//		return itemClassDAO.getAll();
//	}
//
//	/**
//	 * 新增項目種類
//	 * 
//	 * @param po
//	 */
//	public void insertItemClass(ItemClass po) {
//		itemClassDAO.insert(po);
//	}
//
//	/**
//	 * 新增項目
//	 * 
//	 * @param po
//	 */
//	public void insertItem(Item po) {
//		itemDAO.insert(po);
//	}
//
//	/**
//	 * 新增訂單
//	 * 
//	 * @param po
//	 */
////	public void insertBooking(Booking po) {
////		bookingDAO.insert(po);
////	}
//
//	/**
//	 * 更新訂單
//	 * 
//	 * @param po
//	 */
////	public void updateBooking(Booking po) {
////		bookingDAO.update(po);
////	}
//
//	/**
//	 * 新增地圖擺設座標點位
//	 * 
//	 * @param poList
//	 */
//	public void insertSeatPosition(SeatMap map, List<Furnish> poList) {
//		/** delete all */
//		seatPositionDAO.deleteByMapID(map.getMapID());
//
//		/** insert */
//		for (Furnish po : poList) {
//			seatPositionDAO.insert(po);
//		}
//	}
//}

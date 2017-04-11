package com.thirtySix.dao.daoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.dao.BookingDAO;
import com.thirtySix.po.Booking;

@Repository
public class BookingDAOImpl extends GenericDAOImpl<Booking> implements
		BookingDAO {

}

package com.thirtySix.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.BookingDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.dto.FurnishDTO;
import com.thirtySix.dto.ItemDTO;
import com.thirtySix.dto.OrderDTO;
import com.thirtySix.dto.SeatMapDTO;
import com.thirtySix.model.Booking;
import com.thirtySix.model.Customer;
import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.Item;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.MapService;

@Component
public class ObjectConverter {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private MapService mapService = null;

	public Customer customerDTOtoPO(CustomerDTO dto) {
		Customer po = new Customer();

		po.setCustomerID(dto.getCustomerID());
		po.setCheckInTime(dto.getCheckInTime());
		po.setCheckOutTime(dto.getCheckOutTime());
		po.setCustomerName(dto.getCustomerName());
		po.setPhoneNumber(dto.getPhoneNumber());

		Furnish furnish = buffer.getFurnish().get(dto.getFurnishID());
		po.setFurnish(furnish);
		po.setPeopleCount(dto.getPeopleCount());
		po.setRemark(dto.getRemark());

		return po;
	}

	public List<Booking> bookingDTOtoPO(OrderDTO order) {
		List<Booking> bookingList = new ArrayList<Booking>();
		Customer customer = buffer.getDiningCustomer().get(order.getCustomerId());

		for (ItemDTO item : order.getItemList()) {
			Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setOrderTime(order.getTime());
			Item _item = this.buffer.getItems().get(item.getItemId());
			booking.setItem(_item);
			booking.setVolume(item.getVolume());

			bookingList.add(booking);
		}

		return bookingList;
	}

	// public Booking bookingDTOtoPO(OrderDTO dto, String itemId, int volume) {
	// Booking po = new Booking();
	// Customer customer = buffer.getDiningCustomer().get(dto.getCustomerId());
	// po.setCustomer(customer);
	// po.setOrderTime(dto.getTime());
	// Item item = this.buffer.getItems().get(itemId);
	// po.setItem(item);
	// po.setVolume(volume);
	//
	// return po;
	// }

	public SeatMap seatMapDTOtoPO(SeatMapDTO dto) {
		SeatMap po = new SeatMap();

		if (!dto.getMapID().trim().equals(""))
			po.setMapID(dto.getMapID().trim());

		po.setLocation(dto.getLocation());
		po.setWidth(dto.getWidth());
		po.setHeight(dto.getHeight());

		return po;
	}

	public List<Furnish> furnishDTOtoPO(SeatMap mapPO, List<FurnishDTO> dtos) {
		List<Furnish> poList = new ArrayList<Furnish>();

		for (FurnishDTO dto : dtos) {
			Furnish po = new Furnish();

			po.setSeatMap(mapPO);
			po.setX(dto.getX());
			po.setY(dto.getY());
			po.setName(dto.getName());

			/** get furnish class */
			String furnishClassID = dto.getFurnishClassID();
			FurnishClass furnishClass = mapService.findFurnishClass(furnishClassID);

			po.setFurnishClass(furnishClass);

			poList.add(po);
		}

		return poList;
	}

	public CustomerDTO customerPOtoDTO(Customer po) {
		CustomerDTO dto = new CustomerDTO();

		dto.setCustomerID(po.getCustomerID());
		dto.setCustomerName(po.getCustomerName());
		dto.setPeopleCount(po.getPeopleCount());
		dto.setFurnishID(po.getFurnish().getFurnishID());
		dto.setRemark(po.getRemark());
		dto.setPhoneNumber(po.getPhoneNumber());
		dto.setCheckInTime(po.getCheckInTime());
		dto.setCheckOutTime(po.getCheckOutTime());

		List<BookingDTO> dtoList = new ArrayList<BookingDTO>();
		for (Booking bookingPO : po.getBookingList()) {
			dtoList.add(bookingPOtoDTO(bookingPO));
		}
		dto.setBookingList(dtoList);

		return dto;
	}

	public BookingDTO bookingPOtoDTO(Booking po) {
		BookingDTO dto = new BookingDTO();

		dto.setBookingID(po.getBookingID());
		dto.setOrderTime(po.getOrderTime());
		dto.setDeliveryTime(po.getDeliveryTime());
		dto.setVolume(po.getVolume());
		dto.setItem(po.getItem());
		dto.setIsSend(po.getIsSend());

		return dto;
	}
}

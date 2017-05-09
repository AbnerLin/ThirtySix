package com.thirtySix.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.Core.Buffer;
import com.thirtySix.dto.BookingDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.dto.OrderDTO;
import com.thirtySix.dto.SeatMapDTO;
import com.thirtySix.dto.SeatPositionDTO;
import com.thirtySix.po.Booking;
import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;
import com.thirtySix.po.SeatMap;
import com.thirtySix.po.SeatPosition;

@Component
public class ObjectConverter {

	@Autowired
	private Buffer buffer = null;

	public Customer customerDTOtoPO(CustomerDTO dto) {
		Customer po = new Customer();

		po.setCustomerID(dto.getCustomerID());
		po.setCheckInTime(dto.getCheckInTime());
		po.setCheckOutTime(dto.getCheckOutTime());
		po.setCustomerName(dto.getCustomerName());
		po.setPhoneNumber(dto.getPhoneNumber());
		po.setTableNumber(dto.getTableNumber());
		po.setPeopleCount(dto.getPeopleCount());
		po.setRemark(dto.getRemark());

		return po;
	}

	public Booking bookingDTOtoPO(OrderDTO dto, String itemId, int volume) {
		Booking po = new Booking();
		Customer customer = buffer.getDiningCustomer().get(dto.getCustomerId());
		po.setCustomer(customer);
		po.setOrderTime(dto.getTime());
		Item item = this.buffer.getItems().get(itemId);
		po.setItem(item);
		po.setVolume(volume);

		return po;
	}

	public SeatMap seatMapDTOtoPO(SeatMapDTO dto) {
		SeatMap po = new SeatMap();

		if (!dto.getMapID().trim().equals(""))
			po.setMapID(dto.getMapID().trim());

		po.setLocation(dto.getLocation());
		po.setWidth(dto.getWidth());
		po.setHeight(dto.getHeight());

		return po;
	}

	public List<SeatPosition> seatPositionDTOtoPO(SeatMap mapPO,
			List<SeatPositionDTO> dtos) {
		List<SeatPosition> poList = new ArrayList<SeatPosition>();

		for (SeatPositionDTO dto : dtos) {
			SeatPosition po = new SeatPosition();

			po.setSeatMap(mapPO);
			po.setDisplayText(dto.getDisplayText());
			po.setX(dto.getX());
			po.setY(dto.getY());
			poList.add(po);
		}

		return poList;
	}

	public CustomerDTO customerPOtoDTO(Customer po) {
		CustomerDTO dto = new CustomerDTO();

		dto.setCustomerID(po.getCustomerID());
		dto.setCustomerName(po.getCustomerName());
		dto.setPeopleCount(po.getPeopleCount());
		dto.setTableNumber(po.getTableNumber());
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

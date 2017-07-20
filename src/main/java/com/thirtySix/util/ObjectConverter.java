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

	public Customer customerDTOtoPO(final CustomerDTO dto) {
		final Customer po = new Customer();

		po.setCustomerID(dto.getCustomerID());
		po.setCheckInTime(dto.getCheckInTime());
		po.setCheckOutTime(dto.getCheckOutTime());
		po.setCustomerName(dto.getCustomerName());
		po.setPhoneNumber(dto.getPhoneNumber());

		final Furnish furnish = this.buffer.getFurnish()
				.get(dto.getFurnishID());
		po.setFurnish(furnish);
		po.setPeopleCount(dto.getPeopleCount());
		po.setRemark(dto.getRemark());

		return po;
	}

	public List<Booking> bookingDTOtoPO(final OrderDTO order) {
		final List<Booking> bookingList = new ArrayList<Booking>();
		final Customer customer = this.buffer.getDiningCustomer()
				.get(order.getCustomerId());

		for (final ItemDTO item : order.getItemList()) {
			final Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setOrderTime(order.getTime());

			final Item _item = this.buffer.getItems().get(item.getItemId());
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

	public SeatMap seatMapDTOtoPO(final SeatMapDTO dto) {
		final SeatMap po = new SeatMap();

		if (!dto.getMapID().trim().equals(""))
			po.setMapID(dto.getMapID().trim());

		po.setName(dto.getName());
		po.setWidth(dto.getWidth());
		po.setHeight(dto.getHeight());

		return po;
	}

	public List<Furnish> furnishDTOtoPO(final SeatMap mapPO,
			final List<FurnishDTO> dtos) {
		final List<Furnish> poList = new ArrayList<Furnish>();

		for (final FurnishDTO dto : dtos) {
			final Furnish po = new Furnish();

			po.setSeatMap(mapPO);
			po.setX(dto.getX());
			po.setY(dto.getY());
			po.setName(dto.getName());

			/** get furnish class */
			final String furnishClassID = dto.getFurnishClassID();
			final FurnishClass furnishClass = this.mapService
					.findFurnishClass(furnishClassID);

			po.setFurnishClass(furnishClass);

			poList.add(po);
		}

		return poList;
	}

	public CustomerDTO customerPOtoDTO(final Customer po) {
		final CustomerDTO dto = new CustomerDTO();

		dto.setCustomerID(po.getCustomerID());
		dto.setCustomerName(po.getCustomerName());
		dto.setPeopleCount(po.getPeopleCount());
		dto.setFurnishID(po.getFurnish().getFurnishID());
		dto.setRemark(po.getRemark());
		dto.setPhoneNumber(po.getPhoneNumber());
		dto.setCheckInTime(po.getCheckInTime());
		dto.setCheckOutTime(po.getCheckOutTime());

		final List<BookingDTO> dtoList = new ArrayList<BookingDTO>();
		for (final Booking bookingPO : po.getBookingList()) {
			dtoList.add(bookingPOtoDTO(bookingPO));
		}
		dto.setBookingList(dtoList);

		return dto;
	}

	public BookingDTO bookingPOtoDTO(final Booking po) {
		final BookingDTO dto = new BookingDTO();

		dto.setBookingID(po.getBookingID());
		dto.setOrderTime(po.getOrderTime());
		dto.setDeliveryTime(po.getDeliveryTime());
		dto.setVolume(po.getVolume());
		dto.setItem(po.getItem());
		dto.setIsSend(po.getIsSend());

		return dto;
	}
}

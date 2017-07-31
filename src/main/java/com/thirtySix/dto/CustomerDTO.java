// package com.thirtySix.dto;
//
// import java.io.Serializable;
// import java.sql.Timestamp;
// import java.util.Date;
// import java.util.List;
//
// import com.thirtySix.util.TimeFormatter;
//
// public class CustomerDTO implements Serializable {
//
// private static final long serialVersionUID = -8633191169307671028L;
//
// /**
// * 顧客編號
// */
// private String customerID;
//
// /**
// * 桌號
// */
// private String furnishID;
//
// /**
// * 進場時間
// */
// private Timestamp checkInTime;
//
// /**
// * 進場時間 字串格式
// */
// private String checkInTimeStringFormat;
//
// /**
// * 出場時間
// */
// private Timestamp checkOutTime;
//
// /**
// * 出場時間 字串格式
// */
// private String checkOutTimeStringFormat;
//
// /**
// * 顧客名稱
// */
// private String customerName;
//
// /**
// * 顧客電話
// */
// private String phoneNumber;
//
// /**
// * 人數
// */
// private Integer peopleCount;
//
// /**
// * 備註
// */
// private String remark;
//
// /**
// * 訂單資訊
// */
// private List<BookingDTO> bookingList;
//
// /**
// * 取得顧客編號
// *
// * @return
// */
// public String getCustomerID() {
// return customerID;
// }
//
// /**
// * 設定顧客編號
// *
// * @param customerID
// */
// public void setCustomerID(String customerID) {
// this.customerID = customerID;
// }
//
// /**
// * 取得桌號
// *
// * @return
// */
// public String getFurnishID() {
// return furnishID;
// }
//
// /**
// * 設定桌號
// *
// * @param tableNumber
// */
// public void setFurnishID(String furnishID) {
// this.furnishID = furnishID;
// }
//
// /**
// * 取得顧客名稱
// *
// * @return
// */
// public String getCustomerName() {
// return customerName;
// }
//
// /**
// * 設定顧客名稱
// *
// * @param customerName
// */
// public void setCustomerName(String customerName) {
// this.customerName = customerName;
// }
//
// /**
// * 取得顧客電話
// *
// * @return
// */
// public String getPhoneNumber() {
// return phoneNumber;
// }
//
// /**
// * 設定顧客電話
// *
// * @param phoneNumber
// */
// public void setPhoneNumber(String phoneNumber) {
// this.phoneNumber = phoneNumber;
// }
//
// /**
// * 取得備註
// *
// * @return
// */
// public String getRemark() {
// return remark;
// }
//
// /**
// * 設定備註
// *
// * @param remark
// */
// public void setRemark(String remark) {
// this.remark = remark;
// }
//
// /**
// * 取得進場時間
// *
// * @return
// */
// public Timestamp getCheckInTime() {
// return checkInTime;
// }
//
// /**
// * 設定進場時間
// *
// * @param checkInTime
// */
// public void setCheckInTime(Timestamp checkInTime) {
// this.checkInTime = checkInTime;
//
// Date time = new Date(checkInTime.getTime());
// this.setCheckInTimeStringFormat(TimeFormatter.getInstance().getTime(time));
// }
//
// /**
// * 取得出場時間
// *
// * @return
// */
// public Timestamp getCheckOutTime() {
// return checkOutTime;
// }
//
// /**
// * 設定出場時間
// *
// * @param checkOutTime
// */
// public void setCheckOutTime(Timestamp checkOutTime) {
// this.checkOutTime = checkOutTime;
//
// if (this.checkOutTime != null) {
// Date time = new Date(checkOutTime.getTime());
// this.setCheckInTimeStringFormat(TimeFormatter.getInstance().getTime(time));
// }
// }
//
// /**
// * 取得人數
// *
// * @return
// */
// public Integer getPeopleCount() {
// return peopleCount;
// }
//
// /**
// * 設定人數
// *
// * @param peopleCount
// */
// public void setPeopleCount(Integer peopleCount) {
// this.peopleCount = peopleCount;
// }
//
// /**
// * 取得訂單列表
// *
// * @return
// */
// public List<BookingDTO> getBookingList() {
// return bookingList;
// }
//
// /**
// * 設定訂單列表
// *
// * @param bookingList
// */
// public void setBookingList(List<BookingDTO> bookingList) {
// this.bookingList = bookingList;
// }
//
// /**
// * 取得進場時間 字串格式
// *
// * @return
// */
// public String getCheckInTimeStringFormat() {
// return checkInTimeStringFormat;
// }
//
// /**
// * 設定進場時間 字串格式
// *
// * @param checkInTimeStringFormat
// */
// public void setCheckInTimeStringFormat(String checkInTimeStringFormat) {
// this.checkInTimeStringFormat = checkInTimeStringFormat;
// }
//
// /**
// * 取得出場時間 字串格式
// *
// * @return
// */
// public String getCheckOutTimeStringFormat() {
// return checkOutTimeStringFormat;
// }
//
// /**
// * 設定出場時間 字串格式
// *
// * @param checkOutTimeStringFormat
// */
// public void setCheckOutTimeStringFormat(String checkOutTimeStringFormat) {
// this.checkOutTimeStringFormat = checkOutTimeStringFormat;
// }
//
// @Override
// public String toString() {
// String result = "";
//
// result += "ID: " + this.customerID + "\n" + "NAME: " + this.customerName +
// "\n" + "CheckInTime: "
// + this.checkInTime + "\n" + "CheckOutTime: " + this.checkOutTime + "\n" +
// "DeskNumber: "
// + this.furnishID + "\n" + "PeopleCount:" + this.peopleCount + "\n" + "Remark:
// " + this.remark + "\n"
// + "PhoneNumber: " + this.phoneNumber + "\n";
//
// return result;
// }
// }

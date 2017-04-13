package com.thirtySix.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ITEMCLASS")
public class ItemClass {

	/**
	 * 種類編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "CLASSID")
	private String classID;

	/**
	 * 種類名稱
	 */
	@Column(name = "CLASSNAME")
	private String className;

	/**
	 * 種類樣式 bootstrap
	 */
	@Column(name = "STYLE")
	private String style = "panel-default";

	/**
	 * 項目
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "itemClass")
	private List<Item> itemList;

	/**
	 * 取得種類編號
	 * 
	 * @return
	 */
	public String getClassID() {
		return classID;
	}

	/**
	 * 取得種類名稱
	 * 
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 設定種類名稱
	 * 
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 取得種類樣式 (Bootstrap css)
	 * 
	 * @return
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 設定種類樣式(Bootstrap css)
	 * 
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 取得項目列表
	 * 
	 * @return
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * 設定項目列表
	 * 
	 * @param itemList
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}

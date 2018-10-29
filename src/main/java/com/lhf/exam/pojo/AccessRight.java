package com.lhf.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问权限
 * 
 * @author admin
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessRight {
	/**
	 * 数据库中的ID
	 */
	private int rightId;
	/**
	 * 排序
	 */
	private int order;
	/**
	 * 权限名
	 */
	private String rightname;
	/**
	 * 权限对应的uri
	 */
	private String righturi;
	@Override
	public String toString() {
		return "AccessRight [id=" + rightId + ", order=" + order + ", name=" + rightname + ", uri=" + righturi + "]";
	}



}

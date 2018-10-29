package com.lhf.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户对象
 * 
 * @author admin
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	/**
	 * 用户ID
	 */
	private int userid;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户的所有角色
	 */
	private List<Role> roles;

}

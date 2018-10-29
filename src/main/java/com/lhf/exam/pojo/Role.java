package com.lhf.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色
 * 
 * @author admin
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	/**
	 * 角色ID
	 */
	private int roleId;

	/**
	 * 角色名
	 */
	private String rolename;

	public String getName() {
		return rolename;
	}

	public void setName(String name) {
		this.rolename = name;
	}

	/**
	 * 本角色下的所有权限
	 */
	private List<AccessRight> rights;

	public int getId() {
		return roleId;
	}

	public void setId(int id) {
		this.roleId = id;
	}

	public List<AccessRight> getRights() {
		return rights;
	}

	public void setRights(List<AccessRight> rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", name=" + rolename + ", rights=" + rights + "]";
	}

}

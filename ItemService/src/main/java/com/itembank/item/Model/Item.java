package com.itembank.item.Model;

import java.util.List;

import com.itembank.item.Entity.MultipleChoiceQ;
import com.itembank.item.Entity.SubjectiveQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private int id;
	private String domain;	
	private int authorId;
	private String status;
	private int version;
	private int type;
	private List<MultipleChoiceQ> mcq;
	private SubjectiveQ subq;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<MultipleChoiceQ> getMcq() {
		return mcq;
	}
	public void setMcq(List<MultipleChoiceQ> mcq) {
		this.mcq = mcq;
	}
	public SubjectiveQ getSubq() {
		return subq;
	}
	public void setSubq(SubjectiveQ subq) {
		this.subq = subq;
	}

}
